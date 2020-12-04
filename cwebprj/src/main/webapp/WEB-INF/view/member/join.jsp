<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

            <main>
                <h2 class="main title">회원가입 폼</h2>
                
                <div class="breadcrumb" style="margin-top:-20px;">
                    <h3 class="hidden">경로</h3>
                </div>
                
                
                <form id="form1" method="post" enctype="multipart/form-data">
                    <fieldset>
                        <legend class="hidden">회원정보</legend>
                        <table class="table margin-top first">
                            <tbody>                                
                                <tr>
                                    <th><label>아이디</label></th>
                                    <td colspan="3" class="text-align-left indent">
                                        <input id="id-text" type="text" name="id" class="width-half"  required="required" value="" placeholder="영문과 숫자 6~20자 이내" pattern="^\w{6,20}$" />
                                        <input class="btn-text btn-default" type="button" id="id-check-button" value="중복확인" />								
                                    </td>
                                </tr>
                                <tr>
                                    <th><label>비밀번호</label></th>
                                    <td colspan="3" class="text-align-left indent">
                                        <input type="password" name="pwd" class="" required placeholder="비밀번호 입력" />
                                    </td>
                                </tr>
                                <tr>
                                    <th><label>비밀번호 확인</label></th>
                                    <td colspan="3" class="text-align-left indent"><input class="" name="pwd2" type="password" required /></td>
                                </tr>
                                <tr>
                                    <th><label>이름</label></th>
                                    <td colspan="3" class="text-align-left indent"><input class="width-half" name="name" type="text"  value="" required="required"/></td>
                                </tr>
                          
                                <tr>
                                    <th><label>생년월일</label></th>
                                    <td colspan="3" class="text-align-left indent">
                                                   
                                        <input name="birthday" type="date" class="width-half" required placeholder="예) 1996-08-02"  value=""/>
                                    </td>
                                </tr>
                                <tr>
                                    <th><label>핸드폰번호</label></th>
                                    <td colspan="3" class="text-align-left indent"><input name="phone" type="text" class="width-half" pattern="^01[016789]-\d{3,4}-\d{4}$" placeholder="예) 010-1111-2222" required  value=""/></td>
                                </tr>
                                <tr>
                                    <th><label>이메일</label></th>
                                    <td colspan="3" class="text-align-left indent"><input name="email" type="email" class="width-half" required placeholder="예) cdm@cdmlecture.com"  value=""/></td>
                                </tr>                                
                                                                
                                <tr>
                                    <td colspan="4"> <!-- /member/confirm 으로 이동 -->
                                        <input type="hidden" name="" value="" />
                                        <input id="submit-Button" type="submit" name="btn" value="확인" style="height: 30px; margin:20px;" class="btn-text btn-default" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form>
            </main>
