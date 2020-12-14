package com.cdm.web.controller.main;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdm.web.dto.CommunityDTO;
import com.cdm.web.dto.ReplyDTO;
import com.cdm.web.page.PageMaker;
import com.cdm.web.page.SearchCriteria;
import com.cdm.web.service.CommunityService;
import com.cdm.web.service.ReplyService;

@Controller
@RequestMapping("/main/")
public class MainController {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private ReplyService replyService;

	@RequestMapping("intro") // Ȩ������ �Ұ�
	public String intro() {
		return "main/intro";
	}

	@RequestMapping("study") // ���� ����
	public String study() {
		return "main/study";
	}

	@RequestMapping(value = "community/list", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ����Ʈ �ҷ�����
	public ModelAndView communitylist(SearchCriteria searchCriteria) throws Exception {

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(communityService.countSearched(searchCriteria));// ��ü �Խñ�

		ModelAndView mv = new ModelAndView();

		List<CommunityDTO> list = communityService.listSearch(searchCriteria);

		mv.setViewName("/main/community/list"); // list��
		mv.addObject("list", list); // ��� ���� ������
		mv.addObject("pageMaker", pageMaker);

		return mv;
	}

	@RequestMapping("community/register") // Ŀ�´�Ƽ �Խ��� ���
	public String communityRegister() {
		return "main/community/register";
	}

	@RequestMapping(value = "community/write", method = RequestMethod.POST) // Ŀ�´�Ƽ �Խ��� ���
	public void communityWrite(CommunityDTO vo, HttpServletResponse response, MultipartHttpServletRequest mpRequest)
			throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // �ѱ� ���ڵ� ����
		PrintWriter out = response.getWriter(); // ������ ���� ��ü

		communityService.write(vo, mpRequest);

		out.println("<script>alert('��� �Ǿ����ϴ�.'); " + "location.href = '/main/community/list'</script>");

		// �ӽ�
	}

	@RequestMapping(value = "community/detail", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ������
	public ModelAndView detail(@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		ModelAndView mv = new ModelAndView();
	
		mv.addObject("detail", communityService.detail(community_num)); // �󼼺��� ���񽺸� ���� �ش� �Խñ� �ҷ�����

		List<ReplyDTO> replyList = replyService.readReply(community_num); // ��� �ҷ�����
		mv.addObject("replyList", replyList);
		
		List<Map<String, Object>> fileList = communityService.selectFileList(community_num);	//���� �ҷ�����
		mv.addObject("file", fileList);
		
		mv.setViewName("main/community/detail");
		return mv;
	}

	@RequestMapping(value = "community/delete", method = RequestMethod.POST) // Ŀ�´�Ƽ �Խ��� ���� Ŭ�� ��
	public String delete(@RequestParam("community_num") int community_num, SearchCriteria searchCriteria,
			RedirectAttributes redirectAttributes) throws Exception {

		communityService.delete(community_num);

		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // �˻� ���� ����

		return "redirect:/main/community/list";
	}

	@RequestMapping(value = "community/modify", method = RequestMethod.GET) // Ŀ�´�Ƽ �Խ��� ���� ������
	public ModelAndView communityModify(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("community_num") int community_num,
			@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("title", title); // ����� �������� ���� �������� �Ѱ��ش�.
		mv.addObject("content", content);
		mv.addObject("community_num", community_num);
		
		List<Map<String,Object>> fileList = communityService.selectFileList(community_num);	//���� ����Ʈ �ҷ����� 
		mv.addObject("file",fileList);
		
		mv.setViewName("main/community/modify");

		return mv;
	}

	@RequestMapping(value = "community/modify/modifyPost", method = RequestMethod.POST) // Ŀ�´�Ƽ ���� ���������� ���� Ŭ�� ��
	public String modify(CommunityDTO vo, SearchCriteria searchCriteria, RedirectAttributes redirectAttributes
			, @RequestParam(value="fileNoDel[]") String[] files,
			 @RequestParam(value="fileNameDel[]") String[] fileNames,
			 MultipartHttpServletRequest mpRequest)
			throws Exception {

		communityService.modify(vo, files, fileNames, mpRequest);
		redirectAttributes.addAttribute("page", searchCriteria.getPage());
		redirectAttributes.addAttribute("searchType", searchCriteria.getSearchType());
		redirectAttributes.addAttribute("keyword", searchCriteria.getKeyword()); // �˻� ���� ����

		return "redirect:/main/community/list";
	}
	
	@RequestMapping(value="community/detail/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{ //���� �ٿ�ε�
		Map<String, Object> resultMap = communityService.selectFileInfo(map);
		
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		
		// ������ �����ߴ� ��ġ���� ÷�������� �о� byte[]�������� ��ȯ�Ѵ�.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("D:\\Tools\\cwebprj\\file\\"+storedFileName));
		
		response.setContentType("application/octet-stream");	//mime ���� ���� �⺻��
		response.setContentLength(fileByte.length); //ũ�� ����
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		//÷�� ���� ��� ��
		
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}

}
