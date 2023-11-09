package com.swkim.myboard.service.implement;

import java.util.*;
import java.io.*;
import java.net.*;

public class JavaApi{
	public static void main(String[] args) throws IOException {
		/* 예약 API 사용시 여기부터 삭제후 이용 하시기 바랍니다. */

		// URL
		String url = "https://directsend.co.kr/index.php/api_v2/mail_change_word";

		URL obj;
		obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("Cache-Control", "no-cache");
		con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		con.setRequestProperty("Accept", "application/json");

		/*
		 * subject  : 받을 mail 제목, 치환 문자열 사용 가능.
		 *   치환 문자열 : [$NAME] - 이름 (한글 10글자/영문 30byte 처리), [$EMAIL] - 이메일, [$MOBILE] - 휴대폰,
		 *     [$NOTE1] - 비고1 (한글/영문 128자 처리), [$NOTE2] - 비고2 (한글/영문 128자 처리), [$NOTE3] - 비고3 (한글/영문 128자 처리), [$NOTE4] - 비고4 (한글/영문 128자 처리), [$NOTE5] - 비고5 (한글/영문 128자 처리)
		 *   템플릿 사용시 템플릿에 입력된 메일 제목이 우선적으로 적용됩니다. 빌더로 템플릿을 저장할 경우 메일 제목은 저장되지 않으므로 subject값을 입력해주시기 바랍니다.
		 * body  : 받을 mail 본문, 치환 문자열 사용 가능.
		 *   치환 문자열 : [$NAME] - 이름 (한글 10글자/영문 30byte 처리), [$EMAIL] - 이메일, [$MOBILE] - 휴대폰,
		 *     [$NOTE1] - 비고1 (한글/영문 128자 처리), [$NOTE2] - 비고2 (한글/영문 128자 처리), [$NOTE3] - 비고3 (한글/영문 128자 처리), [$NOTE4] - 비고4 (한글/영문 128자 처리), [$NOTE5] - 비고5 (한글/영문 128자 처리)
		 * template : 사이트에 등록한 발송 할 템플릿 번호
		 * sender : 발송자 메일주소
		 * sender_name : 발송자 이름 (35자 제한)
		 * username : directsend 발급 ID
		 * receiver : 발송 할 고객 수신자 정보
		 *   json array. ex)
		 *      [
		 *          {"name": "강길동", "email":"test1@directsend.co.kr", "mobile":"", "note1":"", "note2":"", "note3":"", "note4":"", "note5":""}
		 *          , {"name": "홍길동", "email":"test2@directsend.co.kr", "mobile":"수신자번호", "note1":"다이렉트 센드 2", "note2":"다이렉트센드 2", "note3":"다이렉트센드 3", "note4":"다이렉트센드 4", "note5":"다이렉트센드 5"}
		 *      ]
		 * address_books : 사이트에 등록한 발송 할 주소록 번호 , 로 구분함 (ex. 0,1,2)
		 * duplicate_yn : 수신자 정보가 중복될 경우 중복발송을 할지에 대한 여부
		 * key : directsend 발급 api key
		 *
		 * 각 내용이 유효하지않을 경우에는 발송이 되지 않습니다.
		 * 비고 내용이 최대 길이(한글/영문 128자 처리)를 넘는 경우 최대 길이 만큼 잘려서 치환 됩니다.
		 * 상업성 광고 메일이나 업체 홍보 메일을 발송하는 경우, 제목에 (광고) 문구를 표기해야 합니다.
		 * 영리광고 발송 시, 명시적인 사전 동의를 받은 이에게만 광고 메일 발송이 가능합니다.
		 * 수신동의 여부에 대한 분쟁이 발생하는 경우 이에 대한 입증책임은 광고성 정보 전송자에게 있습니다.
		 * 수신자가 수신거부 또는 수신동의 철회 의사를 쉽게 표시할 수 있는 안내문을 명시해야 합니다.
		 * 스팸 메일 발송 용도로 악용하실 경우 이용에 제한이 있을 수 있으니 이용 시 주의 부탁 드립니다.
		 * 불법 스팸 메일 발송 시 예고없이 서비스 이용이 정지될 수 있으며 이용정지 시 해당 아이디의 주소록과 잔액은 소멸되며, 환불되지 않으니 서비스 이용에 주의를 부탁드립니다.
		 *
		 * API 연동 발송시 다량의 주소를 한번에 입력하여도 수신자에게는 1:1로 보내는 것으로 표기되며, 동일한 내용의 메일을 한건씩 발송하는 것보다 다량으로 한번에 보내는 것이 발송 효율이 더 높습니다.
		 * 동일한 내용의 메일을 일부 글자만 변경하여 다수에게 발송하시는 경우 수신자 정보를 Json Array [{...}, {...}]로 구분하시어 한번에 발송하시는 것을 권장 드립니다.
		 */

		// 여기서부터 수정해주시기 바랍니다.

		String subject = "[회원가입] [$NAME]님 환영합니다. ";   //필수입력(템플릿 사용시 23 line 설명 참조)
		String body = "[$NAME]님 환영합니다. 치환 문자 입니다. 수신 이메일 : [$EMAIL] 수신번호 : [$MOBILE] 비고1 : [$NOTE1] 비고2 : [$NOTE2] 비고3 : [$NOTE3] 비고4 : [$NOTE4] 비고5 : [$NOTE5]";		//필수입력, 템플릿 사용시 빈값을 입력 하시기 바랍니다. 예시) String body = "";
		String sender = "발송자이메일";        //필수입력
		String sender_name = "DirectSend";
		String username = "directsend id";              //필수입력
		String key = "directsend 발급 api key";           //필수입력

		//수신자 정보 추가 - 필수 입력(주소록 미사용시), 치환문자 미사용시 치환문자 데이터를 입력하지 않고 사용할수 있습니다.
		//치환문자 미사용시 {\"email\":\"aaaa@naver.com\"} 이메일만 입력 해주시기 바랍니다.
		String receiver = "{\"name\": \"강길동\", \"email\":\"수신자이메일\", \"mobile\":\"\", \"note1\":\"\", \"note2\":\"\", \"note3\":\"\", \"note4\":\"\", \"note5\":\"\"}"
				+ ",{\"name\": \"홍길동\", \"email\":\"수신자이메일\", \"mobile\":\"수신자번호\", \"note1\":\"다이렉트 센드 1\", \"note2\":\"다이렉트 센드 2\", \"note3\":\"다이렉트 센드 3\", \"note4\":\"다이렉트 센드 4\", \"note5\":\"다이렉트 센드 5\"}"
				;

		receiver = "["+ receiver +"]";

		//템플릿을 사용하길 원하실 경우 아래 주석을 해제하신후, 사이트에 등록한 템플릿 번호를 입력해주시기 바랍니다.
		//String template = "0";        //발송 할 템플릿 번호

		//주소록을 사용하길 원하실 경우 아래 주석을 해제하신 후, 사이트에 등록한 주소록 번호를 입력해주시기 바랍니다.
		//String address_books = "0,1,2";      //발송 할 주소록 번호 , 로 구분함 (ex. 0, 1, 2)

		//수신자 정보가 중복이고 내용이 다를 경우 아래 주석을 해제하시고 발송해주시기 바랍니다.
		//String duplicate_yn = "1";

		//실제 발송성공실패 여부를 받기 원하실 경우 아래 주석을 해제하신 후, 사이트에 등록한 URL 번호를 입력해주시기 바랍니다.
		int return_url = 0;

		//open, click 등의 결과를 받기 원하실 경우 아래 주석을 해제하신 후, 사이트에 등록한 URL 번호를 입력해주시기 바랍니다.
		//등록된 도메인이 http://domain 와 같을 경우, http://domain?type=[click | open | reject]&mail_id=[MailID]&email=[Email]&sendtime=[SendTime]&mail_reserve_id=[MailReserveID] 과 같은 형식으로 request를 보내드립니다.
		int option_return_url = 0;

		int open = 1;	// open 결과를 받으려면 아래 주석을 해제 해주시기 바랍니다.
		int click = 1;	// click 결과를 받으려면 아래 주석을 해제 해주시기 바랍니다.
		int check_period = 3;	// 트래킹 기간을 지정하며 3 / 7 / 10 / 15 일을 기준으로 지정하여 발송해 주시기 바랍니다. (단, 지정을 하지 않을 경우 결과를 받을 수 없습니다.)

		// 예약발송 정보 추가
		String mail_type = "NORMAL"; // NORMAL - 즉시발송 / ONETIME - 1회예약 / WEEKLY - 매주정기예약 / MONTHLY - 매월정기예약
		String start_reserve_time = "2019-03-08 12:11:00";// 발송하고자 하는 시간
		String end_reserve_time = "2019-03-08 12:11:00";// 발송이 끝나는 시간 1회 예약일 경우 start_reserve_time = end_reserve_time
		// WEEKLY | MONTHLY 일 경우에 시작 시간부터 끝나는 시간까지 발송되는 횟수 Ex) type = WEEKLY, start_reserve_time = '2017-05-17 13:00:00', end_reserve_time = '2017-05-24 13:00:00' 이면 remained_count = 2 로 되어야 합니다.
		int remained_count = 1;
		// 예약 수정/취소 API는 소스 하단을 참고 해주시기 바랍니다.

		//필수안내문구 추가
		//String agreement_text = "본메일은 [$NOW_DATE] 기준, 회원님의 수신동의 여부를 확인한 결과 회원님께서 수신동의를 하셨기에 발송되었습니다.";
		//String deny_text = "메일 수신을 원치 않으시면 [$DENY_LINK]를 클릭하세요. \\nIf you don't want this type of information or e-mail, please click the [$EN_DENY_LINK]";
		//String sender_info_text = "사업자 등록번호:-- 소재지:ㅇㅇ시(도) ㅇㅇ구(군) ㅇㅇ동 ㅇㅇㅇ번지 TEL:-- \\nEmail: <a href='mailto:test@directsend.co.kr'>test@directsend.co.kr</a>";
		//int logo_state = 1; // logo 사용시 1 / 사용안할 시 0
		//String logo_path = "http://logoimage.com/image.png';  //사용하실 로고 이미지를 입력하시기 바랍니다.";
		//String logo_sort = "CENTER";  //로고 정렬 LEFT - 왼쪽 정렬 / CENTER - 가운데 정렬 / RIGHT - 오른쪽 정렬
		//String footer_sort = "CENTER";  //메일내용, 풋터(수신옵션) 정렬 LEFT - 왼쪽 정렬 / CENTER - 가운데 정렬 / RIGHT - 오른쪽 정렬

		// 첨부파일의 URL을 보내면 DirectSend에서 파일을 download 받아 발송처리를 진행합니다. 첨부파일은 전체 10MB 이하로 발송을 해야 하며, 파일의 구분자는 '|(shift+\)'로 사용하며 5개까지만 첨부가 가능합니다.
		String file_url = "https://directsend.co.kr/test.png|https://directsend.co.kr/test1.png";
		// 첨부파일의 이름을 지정할 수 있도록 합니다.
		// 첨부파일의 이름은 순차적(https://directsend.co.kr/test.png - image.png, https://directsend.co.kr/test1.png - image2.png) 와 같이 적용이 되며, file_name을 지정하지 않은 경우 마지막의 파일의 이름으로 메일에 보여집니다.
		String file_name = "image.png|image2.png";

		/* 여기까지 수정해주시기 바랍니다. */

		String urlParameters = "\"subject\":\"" + subject + "\" "
				+ ", \"body\":\"" + body + "\" "
				+ ", \"sender\":\"" + sender + "\" "
				+ ", \"sender_name\":\"" + sender_name + "\" "
				+ ", \"username\":\"" + username + "\" "
				+ ", \"receiver\":" + receiver
				//+ ", \"template\":\"" + template + "\" "		        //템플릿 사용할 경우 주석 해제
				//+ ", \"address_books\":\"" + address_books + "\" "	//주소록 사용할 경우 주석 해제
				//+ ", \"duplicate_yn\":\"" + duplicate_yn + "\" "      //중복 발송을 허용할 경우 주석 해제

				// 예약 관련 파라미터 주석 해제
				//+ ", \"mail_type\":\"" + mail_type + "\" "
				//+ ", \"start_reserve_time\":\"" + start_reserve_time + "\" "
				//+ ", \"end_reserve_time\":\"" + end_reserve_time + "\" "
				//+ ", \"remained_count\":\"" + remained_count + "\" "

				// 필수 안내문구 관련 파라미터 주석 해제
				//+ ", \"agreement_text\":\"" + agreement_text + "\" "
				//+ ", \"deny_text\":\"" + deny_text + "\" "
				//+ ", \"sender_info_text\":\"" + sender_info_text + "\" "
				//+ ", \"logo_path\":\"" + logo_path + "\" "
				//+ ", \"logo_state\":\"" + logo_state + "\" "
				//+ ", \"logo_sort\":\"" + logo_sort + "\" "

				// 메일내용, 풋터(수신옵션) 정렬 사용할 경우 주석 해제
				//+ ", \"footer_sort\":\"" + footer_sort + "\" "

				// 메일 발송결과를 받고 싶은 URL     return_url이 있는 경우 주석해제 바랍니다.
				//+ ", \"return_url_yn\": " + true        //return_url 사용시 필수 입력
				//+ ", \"return_url\":\"" + return_url + "\" "		    //return_url 사용시 필수 입력

				// 발송 결과 측정 항목을 사용할 경우 주석 해제
				//+ ", \"open\":\"" + open + "\" "
				//+ ", \"click\":\"" + click + "\" "
				//+ ", \"check_period\":\"" + check_period + "\" "
				//+ ", \"option_return_url\":\"" + option_return_url + "\" "

				// 첨부 파일이 있는 경우 주석 해제
				//+ ", \"file_url\":\"" + file_url + "\" "
				//+ ", \"file_name\":\"" + file_name + "\" "

				+ ", \"key\":\"" + key + "\" ";
		urlParameters = "{"+ urlParameters  +"}";		//JSON 데이터

		System.setProperty("jsse.enableSNIExtension", "false");
		con.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter (con.getOutputStream(), "UTF-8");
		wr.write(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println(responseCode);

		/*
		 * responseCode 가 200 이 아니면 내부에서 문제가 발생한 케이스입니다.
		 * directsend 관리자에게 문의해주시기 바랍니다.
		 */

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();

		System.out.println(response.toString());


		/*
		 * response의 실패
		 * {"status":101, "msg":"UTF-8 인코딩이 아닙니다."}
		 * 실패 코드번호, 내용
		 */

		/*
		 * response 성공
		 * {"status":0}
		 * 성공 코드번호 (성공코드는 다이렉트센드 DB서버에 정상수신됨을 뜻하며 발송성공(실패)의 결과는 발송완료 이후 확인 가능합니다.)
		 *
		 * 잘못된 이메일 주소가 포함된 경우
		 * {"status":0, "msg":"유효하지 않는 이메일을 제외하고 발송 완료 하였습니다.", "msg_detail":"error email : test2@test2, test3@test"}
		 * 성공 코드번호 (성공코드는 다이렉트센드 DB서버에 정상수신됨을 뜻하며 발송성공(실패)의 결과는 발송완료 이후 확인 가능합니다.), 내용, 잘못된 데이터
		 *
		 */

		/*
			status code
			0   : 정상발송 (성공코드는 다이렉트센드 DB서버에 정상수신됨을 뜻하며 발송성공(실패)의 결과는 발송완료 이후 확인 가능합니다.)
			100 : POST validation 실패
			101 : 회원정보가 일치하지 않음
			102 : Subject, Body 정보가 없습니다.
			103 : Sender 이메일이 유효하지 않습니다.
			104 : receiver 이메일이 유효하지 않습니다.
			105 : 본문에 포함되면 안되는 확장자가 있습니다.
			106 : body validation 실패
			107 : 받는사람이 없습니다.
			108 : 예약정보가 유효하지 않습니다.
			109 : return_url이 없습니다.
			110 : 첨부파일이 없습니다.
			111 : 첨부파일의 개수가 5개를 초과합니다.
			112 : 파일의 총Size가 10 MB를 넘어갑니다.
			113 : 첨부파일이 다운로드 되지 않았습니다.
			114 : utf-8 인코딩 에러 발생
			115 : 템플릿 validation 실패
			200 : 동일 예약시간으로는 200회 이상 API 호출을 할 수 없습니다.
			201 : 분당 300회 이상 API 호출을 할 수 없습니다.
			202 : 발송자명이 최대길이를 초과 하였습니다.
			205 : 잔액부족
			999 : Internal Error.
		 */

		/* 예약 API 사용시 여기까지 삭제후 이용 하시기 바랍니다. */

	}

	public void directSendReservation() {
		/* ====================================== 예약 목록 API 시작 ====================================== */
		/*
		 * 예약 목록 조회 API
		 * username : directsend 발급 ID
		 * key : directsend 발급 api key
		 *
		 * 예약목록 조회 -> 예약 번호 확인 -> 예약 수정/예약 취소 -> 예약 목록 조회(수정정보 확인)
		 * 예약번호, 메일번호등 예약정보를 조회하기 위한 API 입니다.
		 * 예약 목록을 조회하여 JSON 형식으로 전달 됩니다.
		 * JSON형식 POST값으로 전달 해야 됩니다.
		 * 예약 목록 API 사용시 주석 해제후 사용 하시기 바랍니다.
		 */

		/* 여기서부터 수정해주시기 바랍니다. */

		String username = "DirectSend id";                //필수입력
		String key = "DirectSend 발급 api key";           //필수입력

		/* 여기까지 수정해주시기 바랍니다. */

		String postvars = "{\"username\":\""+ username +"\",\"key\":\""+ key +"\"}";		//JSON 데이터

		String url = "https://directsend.co.kr/index.php/api_mail_reserve/get";        //URL

		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			con.setRequestProperty("Accept", "application/json");

			System.setProperty("jsse.enableSNIExtension", "false");
			con.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter (con.getOutputStream(), "UTF-8");
			wr.write(postvars);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			/*
			 * responseCode 가 200 이 아니면 내부에서 문제가 발생한 케이스입니다.
			 * directsend 관리자에게 문의해주시기 바랍니다.
			 */

			java.io.BufferedReader in = new java.io.BufferedReader(
				new java.io.InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			System.out.println(response.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		/*
		 * response 성공 json 데이터 양식
		 * {result:1, message:"success", data:{~~}}
		 *   data:total_count 예약 목록 전체 갯수
		 *   data:list[~~]  예약 목록 정보
		 *      번호	mail_reserve_no
		 *      발송총횟수	mail_reserve_count
		 *      등록일	mail_reserve_date
		 *      발송시작일	mail_reserve_startdate
		 *      발송종료일	mail_reserve_enddate
		 *      예약유형	mail_reserve_type       1 : 1회 / 2 : 매주 / 3 : 매달
		 *      발송 총 건수	mail_send_count
		 *      발송자 이메일	mail_sender_email
		 *      메일 제목	mail_subject
		 *      인증여부	mail_reserve_auth_flag      0 : 비인증 / 1 : 인증
		 *      메일 번호	mail_no
		 */

		/*
		 * response 실패 json 데이터 양식
		 * {result:100-1, message:에러 내용}
		 */

		/*
			status code
			1 : 성공
			100-1 : 파라미터 오류
			100-2 : 예약 목록 조회 오류
		 */
		/* ====================================== 예약 목록 API 끝 ====================================== */


	}

	public void getDirectSendMailReservation() {
		/* ====================================== 예약 번호별 정보 조회 API 시작 ====================================== */
		/*
		 * 예약 목록 조회 API
		 * username : directsend 발급 ID
		 * key : directsend 발급 api key
		 * reserve_no : directsend 예약 번호
		 *
		 * 예약번호별 예약정보를 조회하기 위한 API 입니다.
		 * 예약 정보를 조회하여 JSON 형식으로 전달 됩니다.
		 * JSON형식 POST값으로 전달 해야 됩니다.
		 * 예약 번호별 정보 조회 API 사용시 주석 해제후 사용 하시기 바랍니다.
		 */

		/* 여기서부터 수정해주시기 바랍니다. */

		//String username = "DirectSend id";                //필수입력
		//String key = "DirectSend 발급 api key";           //필수입력
		//String reserve_no = "DirectSend 예약번호";        //필수입력

		/* 여기까지 수정해주시기 바랍니다. */

		//String postvars = "{\"username\":\""+ username +"\",\"key\":\""+ key +"\",\"reserve_no\":\""+ reserve_no +"\"}";		//JSON 데이터

		//String url = "https://directsend.co.kr/index.php/api_mail_reserve/get";        //URL

		//java.net.URL obj;
		//obj = new java.net.URL(url);
		//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//con.setRequestProperty("Cache-Control", "no-cache");
		//con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		//con.setRequestProperty("Accept", "application/json");

		//System.setProperty("jsse.enableSNIExtension", "false");
		//con.setDoOutput(true);
		//OutputStreamWriter wr = new OutputStreamWriter (con.getOutputStream(), "UTF-8");
		//wr.write(postvars);
		//wr.flush();
		//wr.close();

		//int responseCode = con.getResponseCode();
		//System.out.println(responseCode);

		/*
		 * responseCode 가 200 이 아니면 내부에서 문제가 발생한 케이스입니다.
		 * directsend 관리자에게 문의해주시기 바랍니다.
		 */

		//java.io.BufferedReader in = new java.io.BufferedReader(
		//	new java.io.InputStreamReader(con.getInputStream(), "UTF-8"));
		//String inputLine;
		//StringBuffer response = new StringBuffer();

		//while ((inputLine = in.readLine()) != null) {
		//	response.append(inputLine);
		//}

		//in.close();

		//System.out.println(response.toString());

		/*
		 * response 성공 json 데이터 양식
		 * {result:1, message:"success", data:{~~}}
		 *   data:list[~~]  예약 정보
		 *      번호	        mail_reserve_no
		 *      발송총횟수	mail_reserve_count
		 *      등록일	    mail_reserve_date
		 *      발송시작일	mail_reserve_startdate
		 *      발송종료일	mail_reserve_enddate
		 *      예약유형	    mail_reserve_type       1 : 1회 / 2 : 매주 / 3 : 매달
		 *      발송 총 건수	mail_send_count
		 *      발송자 이메일	mail_sender_email
		 *      메일 제목	mail_subject
		 *      인증여부	    mail_reserve_auth_flag      0 : 비인증 / 1 : 인증
		 *      메일 번호	mail_no
		 */

		/*
		 * response 실패 json 데이터 양식
		 * {result:100-1, message:에러 내용}
		 */

		/*
			status code
			1 : 성공
			100-1 : 파라미터 오류
			100-2 : 예약 정보 조회 오류
		 */
		/* ====================================== 예약 번호별 정보 조회 API 끝 ====================================== */

		/* ====================================== 예약 수정/취소 API 시작 ====================================== */
		/*
		 * 예약 수정/취소 API
		 * username : directsend 발급 ID
		 * key : directsend 발급 api key
		 * reserve_no : directsend 예약 번호
		 * mail_no : directsend 메일 번호
		 * reserve_type : 예약 유형, 예약 수정 API 사용시 필수 입력
		 * mail_reserve_startdate : 발송하고자 하는 시간(시,분단위까지만 가능), 예약 수정 API 사용시 필수 입력
		 * mail_reserve_enddate : 발송이 끝나는 시간, 예약 수정 API 사용시 필수 입력
		 *
		 * 예약 수정/취소 API에서 사용 하는 예약번호, 메일번호는 예약 목록 API를 통하여 확인하실 수 있습니다.
		 * 예약 목록을 조회하여 예약 수정/취소 정보를 JSON 형식으로 전달 해야 됩니다.
		 * 예약 API 사용시 주석 해제후 사용 하시기 바랍니다.
		 */

		/* 여기서부터 수정해주시기 바랍니다. */

		//String username = "DirectSend id";                //필수입력
		//String key = "DirectSend 발급 api key";           //필수입력
		//String reserve_no = "DirectSend 예약번호";                   //예약 번호 (예약목록 조회 API를 사용하여 확인), 필수입력
		//String mail_no = "DirectSend 메일 번호";                      //메일 번호 (예약목록 조회 API를 사용하여 확인), 필수입력

		// 예약 수정 API 사용시 주석해제
		//String reserve_type = "1";		//필수입력  1 : 1회 / 2 : 매주 / 3 : 매달
		//String mail_reserve_startdate = "2019-05-11 12:11:00";// 발송하고자 하는 시간
		//String mail_reserve_enddate = "2019-05-11 12:11:00";// 발송이 끝나는 시간 1회 예약일 경우 mail_reserve_startdate = mail_reserve_enddate

		/* 여기까지 수정해주시기 바랍니다. */

		//String postvars = "\"username\":\""+ username + "\""
		//	+",\"key\":\""+ key +"\""
		//	+",\"reserve_no\":\""+ reserve_no +"\""
		//	+",\"mail_no\":\""+ mail_no +"\""
		//	+",\"reserve_type\":\""+ reserve_type +"\""
		//	+",\"mail_reserve_startdate\":\""+ mail_reserve_startdate +"\""
		//	+",\"mail_reserve_enddate\":\""+ mail_reserve_enddate +"\""
		//;
		//postvars = "{"+ postvars +"}";

		//String url = "";

		//예약 수정 API 사용시 주석 해제
		//url = "https://directsend.co.kr/index.php/api_mail_reserve/put";        //URL

		//예약 취소 API 사용시 주석 해제
		//url = "https://directsend.co.kr/index.php/api_mail_reserve/delete";        //URL

		//java.net.URL obj;
		//obj = new java.net.URL(url);
		//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//con.setRequestProperty("Cache-Control", "no-cache");
		//con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		//con.setRequestProperty("Accept", "application/json");

		//System.setProperty("jsse.enableSNIExtension", "false");
		//con.setDoOutput(true);
		//OutputStreamWriter wr = new OutputStreamWriter (con.getOutputStream(), "UTF-8");
		//wr.write(postvars);
		//wr.flush();
		//wr.close();

		//int responseCode = con.getResponseCode();
		//System.out.println(responseCode);

		/*
		 * responseCode 가 200 이 아니면 내부에서 문제가 발생한 케이스입니다.
		 * directsend 관리자에게 문의해주시기 바랍니다.
		 */

		//java.io.BufferedReader in = new java.io.BufferedReader(
		//	new java.io.InputStreamReader(con.getInputStream(), "UTF-8"));
		//String inputLine;
		//StringBuffer response = new StringBuffer();

		//while ((inputLine = in.readLine()) != null) {
		//	response.append(inputLine);
		//}

		//in.close();

		//System.out.println(response.toString());

		/*
		 * response 성공 json 데이터 양식
		 * {result:1, message:success}
		 */

		/*
		 * response 실패 json 데이터 양식
		 * {result:100-1, message:에러 내용}
		 */

		/*
			status code
			1 : 성공
			100-3 : 예약 정보 오류
			100-4 : 예약 수정 처리 오류
			100-5 : 예약 취소 처리 오류
		 */
		/* ====================================== 예약 수정/취소 API 끝 ====================================== */
	}
}
