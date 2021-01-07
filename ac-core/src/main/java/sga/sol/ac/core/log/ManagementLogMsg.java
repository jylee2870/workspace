package sga.sol.ac.core.log;

import java.util.Hashtable;

@SuppressWarnings({"unchecked","rawtypes"})
public class ManagementLogMsg {

	private Hashtable msg = new Hashtable();
	
	private Hashtable userLevelMsg = new Hashtable();
	
	protected void initMessage() {
		///////////////////////////////////////////////////////////////////////////////////
		userLevelMsg.put("1", new ManagementLogLan(
				"최고관리자"
				,""
				,""
				,""
				));
		
		userLevelMsg.put("2", new ManagementLogLan(
				"관리자"
				,""
				,""
				,""
				));
		
		userLevelMsg.put("3", new ManagementLogLan(
				"사용자"
				,""
				,""
				,""
				));
		
		userLevelMsg.put("4", new ManagementLogLan(
				"ARS승인자"
				,""
				,""
				,""
				));
		
		
		///////////////////////////////////////////////////////////////////////////////////
		msg.put("90001", new ManagementLogLan(
				"있음"
				,""
				,""
				,""
				));
		
		msg.put("90002", new ManagementLogLan(
				"없음"
				,""
				,""
				,""
				));
		
		msg.put("90003", new ManagementLogLan(
				"YES"
				,""
				,""
				,""
				));
		
		msg.put("90004", new ManagementLogLan(
				"NO"
				,""
				,""
				,""
				));
		
		msg.put("90005", new ManagementLogLan(
				"사용"
				,""
				,""
				,""
				));
		
		msg.put("90006", new ManagementLogLan(
				"사용안함"
				,""
				,""
				,""
				));
		
		
		msg.put("00001", new ManagementLogLan(
				"사용자추가(사용자ID:%s, 사용자명:%s, 권한:%s)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		
		msg.put("00002", new ManagementLogLan(
				"사용자수정(사용자ID:%s, 사용자명:%s, 권한:%s)"
				,""
				,""
				,""
				));
		msg.put("00003", new ManagementLogLan(
				"사용자삭제(사용자ID:%s, 사용자명:%s, 권한:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00004", new ManagementLogLan(
				"사용자비밀번호수정(사용자ID:%s, 사용자명:%s, 권한:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00005", new ManagementLogLan(
				"ARS사용자비밀번호수정(사용자ID:%s, 사용자명:%s, 권한:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00006", new ManagementLogLan(
				"공지사항추가(제목:%s, 내용:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00007", new ManagementLogLan(
				"공지사항수정(제목:%s, 내용:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00008", new ManagementLogLan(
				"공지사항삭제(제목:%s, 내용:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00009", new ManagementLogLan(
				"PKI정보삭제(사용자명:%s, 사용자ID:%s, subject:%s, issuer:%s, 기간:%s ~ %s)"
				,""
				,""
				,""
				));

		msg.put("00010", new ManagementLogLan(
				"프로토콜추가(프로토콜명:%s, 포트:%d)"
				,""
				,""
				,""
				));
		
		msg.put("00011", new ManagementLogLan(
				"프로토콜수정(프로토콜명:%s, 포트:%d)"
				,""
				,""
				,""
				));
		
		msg.put("00012", new ManagementLogLan(
				"프로토콜삭제(프로토콜명:%s, 포트:%d)"
				,""
				,""
				,""
				));
		
		msg.put("00013", new ManagementLogLan(
				"장비추가(장비명:%s, 장비별칭:%s, IP:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00014", new ManagementLogLan(
				"장비수정(장비명:%s, 장비별칭:%s, IP:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00015", new ManagementLogLan(
				"장비삭제(장비명:%s, 장비별칭:%s, IP:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00016", new ManagementLogLan(
				"장비계정추가(장비명:%s,  장비계정:%s, 장비계정별칭:%s, " +
				"별칭사용여부:%s, domain:%s, shell:%s, homedir:%s, 유효기간제한:%s, 유효기간:%s, " +
				"비밀번호유효기간제한:%s, 비밀번호유효기간:%s, 최대동시접속자수:%d" +				
				")"
				,""
				,""
				,""
				));
		
		msg.put("00017", new ManagementLogLan(
				"장비계정수정(장비명:%s,  장비계정:%s, 장비계정별칭:%s, " +
				"별칭사용여부:%s, domain:%s, shell:%s, homedir:%s, 유효기간제한:%s, 유효기간:%s, " +
				"비밀번호유효기간제한:%s, 비밀번호유효기간:%s, 최대동시접속자수:%d" +	
				")"
				,""
				,""
				,""
				));
		
		msg.put("00018", new ManagementLogLan(
				"장비계정삭제((장비명:%s,  장비계정:%s, 장비계정별칭:%s, " +
				"별칭사용여부:%s, domain:%s, shell:%s, homedir:%s, 유효기간제한:%s, 유효기간:%s, " +
				"비밀번호유효기간제한:%s, 비밀번호유효기간:%s, 최대동시접속자수:%d" +
				")"
				,""
				,""
				,""
				));
		
		msg.put("00019", new ManagementLogLan(
				"서비스추가(서비스명:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00020", new ManagementLogLan(
				"서비스삭제(서비스명:%s)"				
				,""
				,""
				,""
				));
		
		
		msg.put("00021", new ManagementLogLan(				
				"제조사추가(회사명:%s, 담당자:%s, 전화번호:%s, 휴대폰번호:%s, 이메일주소:%s, FAX:%s, 설명:%s)"				
				,""
				,""
				,""
				));

		msg.put("00022", new ManagementLogLan(
				"제조사삭제(회사명:%s, 담당자:%s, 전화번호:%s, 휴대폰번호:%s, 이메일주소:%s, FAX:%s, 설명:%s)"				
				,""
				,""
				,""
				));
		
		
		msg.put("00023", new ManagementLogLan(				
				"OS정보추가(OS명:%s, 시스템아키텍쳐:%s, 버전:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00024", new ManagementLogLan(
				"OS정보삭제(OS명:%s, 시스템아키텍쳐:%s, 버전:%s)"				
				,""
				,""
				,""
				));
				
		
		msg.put("00025", new ManagementLogLan(
				"사용자MAC정보추가(사용자ID:%s, 사용자명:%s, MAC:%s, 설명:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00026", new ManagementLogLan(
				"사용자MAC정보삭제(사용자ID:%s, 사용자명:%s, MAC:%s, 설명:%s"				
				,""
				,""
				,""
				));
		
		msg.put("00027", new ManagementLogLan(
				"사용자IP정보삭제(사용자ID:%s, 사용자명:%s, IP:%s, 서브넷마스크:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00028", new ManagementLogLan(				
				"사용자기간설정(사용자ID:%s, 사용자명:%s, 기간:%s)"				
				,""
				,""
				,""
				));
 
		msg.put("00029", new ManagementLogLan(
				"사용자예외시간설정(사용자ID:%s, 사용자명:%s, 작업기간:%s ~ %s, IP:%s, 서브넷마스크:%s, MAC:%s, 설명:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00030", new ManagementLogLan(
				"사용자예외시간수정(사용자ID:%s, 사용자명:%s, 작업기간:%s ~ %s, IP:%s, 서브넷마스크:%s, MAC:%s, 설명:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00031", new ManagementLogLan(
				"사용자예외시간삭제(사용자ID:%s, 사용자명:%s, 작업기간:%s ~ %s, IP:%s, 서브넷마스크:%s, MAC:%s, 설명:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00032", new ManagementLogLan(				
				"장비그룹추가(그룹명:%s, 그룹경로:%s)"				
				,""
				,""
				,""
				));
		
		
		
		msg.put("00033", new ManagementLogLan(
				"장비그룹삭제(그룹명:%s, 그룹경로:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00034", new ManagementLogLan(				
				"장비그룹이동,(장비명:%s, 대상그룹명:%s, 대상그룹경로:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00035", new ManagementLogLan(
				"사용자그룹추가(그룹명:%s, 그룹경로:%s)"
				,""
				,""
				,""
				));

		
		msg.put("00036", new ManagementLogLan(				
				"사용자그룹수정, 그룹(그룹명:%s, 기존경로:%s, 새경로:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00037", new ManagementLogLan(				
				"사용자그룹삭제, 그룹(그룹명:%s, 그룹경로:%s) "				
				,""
				,""
				,""
				));

		msg.put("00038", new ManagementLogLan(				
				"사용자그룹이동, 사용자(사용자ID:%s, 사용자명:%s, 권한:%s)를 그룹(그룹명:%s, 그룹경로:%s)으로이동 "				
				,""
				,""
				,""
				));
		
		msg.put("00039", new ManagementLogLan(				
				"명령어그룹추가(그룹명:%s, 설명:%s) "				
				,""
				,""
				,""
				));
		
		msg.put("00040", new ManagementLogLan(				
				"명령어그룹수정(그룹명:%s, 설명:%s) "				
				,""
				,""
				,""
				));
		
		msg.put("00041", new ManagementLogLan(				
				"명령어그룹삭제(그룹명:%s, 설명:%s) "				
				,""
				,""
				,""
				));
		
		msg.put("00042", new ManagementLogLan(
				"명령어추가(명령어명:%s, 정규표현식사용여부:%s, 설명:%s) "				
				,""
				,""
				,""
				));
		
		msg.put("00043", new ManagementLogLan(
				"명령어수정(명령어명:%s, 정규표현식사용여부:%s, 설명:%s) "				
				,""
				,""
				,""
				));
		
		msg.put("00044", new ManagementLogLan(
				"명령어삭제(명령어명:%s, 정규표현식사용여부:%s, 설명:%s)"				
				,""
				,""
				,""
				));
		// Gateway정보추가는 없어진 기능.
		msg.put("00045", new ManagementLogLan(
				"Gateway정보추가(protocol:%s, gatewayName:%s, gatewayIp:%s, gatewayPort:%s, gatewayNatIp:%s, gatewayNatPort:%s, gatewayNatFlag:%d ) "				
				,""
				,""
				,""
				));
		
		msg.put("00046", new ManagementLogLan(				
				"Gateway정보수정(G/W명:%s, 프로토콜:%s, IP:%s, 포트:%s, NAT IP:%s, NAT 포트:%s, Heartbeat주기:%d) "				
				,""
				,""
				,""
				));
		
		msg.put("00047", new ManagementLogLan(
				"Gateway정보삭제(G/W명:%s, 프로토콜:%s, IP:%s, 포트:%s, NAT IP:%s, NAT 포트:%s, Heartbeat주기:%d) "				
				,""
				,""
				,""
				));
		
		msg.put("00048", new ManagementLogLan(				
				"ARS장비추가(장비명:%s, 장비별칭:%s, ARS승인자(ARS승인자ID:%s, ARS승인자명:%s) "				
				,""
				,""
				,""
				));

		msg.put("00049", new ManagementLogLan(
				//"ARS장비삭제(ARS승인자:(recid:%d, UserId:%s, name:%s, level:%d)에대한 장비(recid:%d, 장비명:%s, 장비별칭:%s)삭제) "
				"ARS장비삭제(장비명:%s, 장비별칭:%s, ARS승인자(ARS승인자ID:%s, ARS승인자명:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00050", new ManagementLogLan(
				//"ARS승인자변경(장비(recid:%d, 장비명:%s, 장비별칭:%s)의 ARS승인자변경. ARS승인자:%s "				
				"ARS승인자변경(장비명:%s, 장비별칭:%s, ARS승인자:%s) "				
				,""
				,""
				,""
				));
		
		msg.put("00051", new ManagementLogLan(				
				"장비그룹수정(그룹명:%s, 그룹경로:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00052", new ManagementLogLan(
				"서비스수정(서비스명:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00053", new ManagementLogLan(				
				"사용자IP정보추가(사용자ID:%s, 사용자명:5s, IP:%s, 서브넷마스크:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00054", new ManagementLogLan(				
				"로그인시도(사용자ID:%s)존재하지 않는 계정"				
				,""
				,""
				,""
				));
		
		msg.put("00055", new ManagementLogLan(				
				"로그인시도(사용자ID:%s)잠겨진 계정"				
				,""
				,""
				,""
				));
		
		msg.put("00056", new ManagementLogLan(				
				"로그인시도(사용자ID:%s)기한이 만료된 계정"				
				,""
				,""
				,""
				));
		
		msg.put("00057", new ManagementLogLan(				
				"로그인시도(사용자ID:%s)비밀번호틀림"				
				,""
				,""
				,""
				));
		
		msg.put("00058", new ManagementLogLan(				
				"로그인(사용자ID:%s, 사용자명:%s, 권한:%s)"				
				,""
				,""
				,""
				));
		
		msg.put("00059", new ManagementLogLan(				
				"로그아웃(사용자ID:%s, 사용자명:%s, 권한:%s)"
				,""
				,""
				,""
				));
		
		msg.put("00060", new ManagementLogLan(
				"사용자IP정보삭제(사용자ID:%s, 사용자명:%s, IP:%s, 서브넷마스크:%s)"				
				,""
				,""
				,""
				));
		
		
		msg.put("00061", new ManagementLogLan(
				"(사용자ID:%s, 사용자명:%s)"				
				,""
				,""
				,""
				));
		msg.put("00062", new ManagementLogLan(
				"최고관리자추가(관리자ID:%s, 관리자명:%s, 권한:%s)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00063", new ManagementLogLan(
				"관리자추가(관리자ID:%s, 관리자명:%s, 권한:%s)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00064", new ManagementLogLan(
				"ARS승인자추가(ARS승인자ID:%s, ARS승인자명:%s, 권한:%s)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));

		// OTP
		msg.put("00065", new ManagementLogLan(
				"OTP사용자지정(OTP씨리얼번호:%s, 사용자를 찾을 수 없음)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00066", new ManagementLogLan(
				"OTP사용자지정(OTP사용자ID:%s, OTP사용자명:%s, OTP씨리얼번호:%s, 이미 다른 OTP가 지정된 사용자)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00067", new ManagementLogLan(
				"OTP사용자지정(OTP사용자ID:%s, OTP사용자명:%s, OTP씨리얼번호:%s, OTP정보를 찾을 수 없음)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00068", new ManagementLogLan(
				"OTP사용자지정(OTP사용자ID:%s, OTP사용자명:%s, OTP씨리얼번호:%s, 이미 다른 사용자에게 지정된 OTP)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00069", new ManagementLogLan(
				"OTP사용자할당(OTP사용자ID:%s, OTP사용자명:%s, OTP씨리얼번호:%s, 사용자의 OTP정보를 갱신할 수 없음)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00070", new ManagementLogLan(
				"OTP사용자지정(OTP사용자ID:%s, OTP사용자명:%s, OTP씨리얼번호:%s)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00071", new ManagementLogLan(
				"OTP사용자해지(OTP씨리얼번호가 없음)"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00072", new ManagementLogLan(
				"OTP사용자해지(사용자의 OTP정보를 갱신할 수 없음, [%s])"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		msg.put("00073", new ManagementLogLan(
				"OTP사용자해지([%s])"	// 사용자권한 : 관리자 ,...
				,""
				,""
				,""
				));
		
		msg.put("00074", new ManagementLogLan(
				"G/W정보수정(G/W명:%s, 접속지연허용시간:%d, Heartbeat Cycle:%d)"
				,""
				,""
				,""
				));
		msg.put("00075", new ManagementLogLan(
				"G/W삭제(삭제할 G/W가 없음)"
				,""
				,""
				,""
				));
		msg.put("00076", new ManagementLogLan(
				"G/W삭제(redid:[%s])"
				,""
				,""
				,""
				));
		msg.put("00077", new ManagementLogLan(
				"G/W삭제(redid:[%s])"
				,""
				,""
				,""
				));
		msg.put("00078", new ManagementLogLan(
				"장비계정새로고침설정, 장비명:(%s)"
				,""
				,""
				,""
				));
		
		msg.put("00079", new ManagementLogLan(
				"장비계정잠금-설정, 계정명:(%s)"
				,""
				,""
				,""
				));
		msg.put("00080", new ManagementLogLan(
				"장비계정-잠금해재설정, 계정명:(%s)"
				,""
				,""
				,""
				));
		//2014.10.20 hwbaek 환경설정-인증설정 로그
		msg.put("00081", new ManagementLogLan(
				"인증 설정, OTP(인증유효기간:(%s), 사용여부:(%s), PKI(인증유효기간:(%s), 사용여부:(%s), ARS(인증유효기간:(%s), 사용여부:(%s), 최대로그인실패횟수:(%s)"
				,""
				,""
				,""
				));
		//2014.10.20 hwbaek 환경설정-화면설정 로그
		msg.put("00082", new ManagementLogLan(
				"화면 설정, 페이지 리스트 갯수:(%s)"
				,""
				,""
				,""
				));
		//2014.10.20 hwbaek 환경설정-알림설정-SMTP설정 로그
		msg.put("00083", new ManagementLogLan(
				"MAIL 설정, SMTP 주소:(%s), SMTP 포트:(%s), SMTP 인증ID:(%s)"
				,""
				,""
				,""
				));
	}
	
	public ManagementLogMsg() {
		initMessage();
	}
	
	/**
	 * 가변 인수로 메시지 얻기
	 * 
	 * @param lang 메시지 언어
	 * @param key 메시지 정의
	 * @param args 메시지에 따른 가변 값
	 * @return
	 * 
	 * @history: 2015.7.13 surfree 추가
	 */
	public String getMsg(String lang, String key, Object... args) {
		String format = getMsg(lang, key);
		
		return String.format(format, args);
	}
	
	public String makeMsg(String format, Object... args) {
		return String.format(format, args);
	}
	
	public String getMsg(String lan, String key)
	{
		ManagementLogLan logLan =  (ManagementLogLan)this.msg.get(key);
		if(logLan != null)
		{
			//if( lan.equals("ko"))
			return logLan.getKo();
		}
		return "";
	}
	
	public String getMsgCondition(boolean condition, String lang, String key1, String key2)
	{
		String key;
		
		if(condition) {
			key = key1;
		}
		else {
			key = key2;
		}
		
		return getMsg(lang, key);
	}
	
	public String getUserLevelMsg(String lan, String key)
	{
		ManagementLogLan logLan =  (ManagementLogLan)this.userLevelMsg.get(key);
		if( logLan != null)
		{
			//if( lan.equals("ko"))
			return logLan.getKo();
		}
		return "";
	}
}
