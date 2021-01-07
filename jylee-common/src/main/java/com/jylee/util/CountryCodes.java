package com.jylee.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class CountryCodes {
    private static Log logger = LogFactory.getLog(CountryCodes.class);
    
    private List<String> countryList = new ArrayList<String>();
    private Map<String,String> krCountryMap = new TreeMap<String,String>();
    private Map<String,String> enCountryMap = new TreeMap<String,String>();
    
    public CountryCodes() {
        
        try {
            initCountryCode();
            String[] countries = Locale.getISOCountries();
            for(String country : countries) {
                Locale countryLocale = new Locale("", country);
                if(countryList.contains(countryLocale.getCountry())) {
                    // System.out.println(countryLocale.getCountry()+" "+countryLocale.getDisplayCountry(Locale.KOREA)+" "+countryLocale.getDisplayCountry(Locale.ENGLISH));
                    krCountryMap.put(countryLocale.getCountry(), countryLocale.getDisplayCountry(Locale.KOREA));
                    enCountryMap.put(countryLocale.getCountry(), countryLocale.getDisplayCountry(Locale.ENGLISH));
                }
            }
            
        } catch(Exception e) {
            logger.error("error ***** "+e.getMessage(), e);
        }
    }
    
    private void initCountryCode() {
        countryList.add("KR");countryList.add("GH");countryList.add("GA");countryList.add("GT");countryList.add("GU");countryList.add("GR");
        countryList.add("GL");countryList.add("GN");countryList.add("NA");countryList.add("NG");countryList.add("ZA");
        countryList.add("NL");countryList.add("NP");countryList.add("NO");countryList.add("NZ");countryList.add("NE");
        countryList.add("NI");countryList.add("TW");countryList.add("DK");countryList.add("DM");countryList.add("DO");
        countryList.add("DE");countryList.add("LA");countryList.add("LV");countryList.add("RU");countryList.add("LB");
        countryList.add("RO");countryList.add("RW");countryList.add("LU");countryList.add("LR");countryList.add("LY");
        countryList.add("LT");countryList.add("MG");countryList.add("MK");countryList.add("MY");countryList.add("ML");
        countryList.add("MT");countryList.add("MX");countryList.add("MC");countryList.add("MA");countryList.add("MZ");
        countryList.add("MV");countryList.add("MN");countryList.add("US");countryList.add("MM");countryList.add("BH");
        countryList.add("BS");countryList.add("BD");countryList.add("BY");countryList.add("VE");countryList.add("VN");
        countryList.add("BE");countryList.add("BO");countryList.add("BT");countryList.add("MP");countryList.add("BG");
        countryList.add("BR");countryList.add("BN");countryList.add("SA");countryList.add("SN");countryList.add("LC");
        countryList.add("SR");countryList.add("LK");countryList.add("SE");countryList.add("CH");countryList.add("ES");
        countryList.add("SK");countryList.add("SI");countryList.add("SG");countryList.add("AE");countryList.add("AM");
        countryList.add("AR");countryList.add("IS");countryList.add("HT");countryList.add("AZ");countryList.add("AF");
        countryList.add("AL");countryList.add("DZ");countryList.add("AO");countryList.add("EC");countryList.add("SV");
        countryList.add("GB");countryList.add("YE");countryList.add("OM");countryList.add("AU");countryList.add("AT");
        countryList.add("HN");countryList.add("JO");countryList.add("UG");countryList.add("UY");countryList.add("UZ");
        countryList.add("UA");countryList.add("ET");countryList.add("IQ");countryList.add("IR");countryList.add("IL");
        countryList.add("EG");countryList.add("IT");countryList.add("IN");countryList.add("ID");countryList.add("JP");
        countryList.add("JM");countryList.add("ZM");countryList.add("CN");countryList.add("MO");countryList.add("HK");
        countryList.add("ZW");countryList.add("CZ");countryList.add("CS");countryList.add("CL");countryList.add("CA");
        countryList.add("KZ");countryList.add("QA");countryList.add("KH");countryList.add("KE");countryList.add("CR");
        countryList.add("CO");countryList.add("CG");countryList.add("CU");countryList.add("KW");countryList.add("HR");
        countryList.add("KG");countryList.add("TZ");countryList.add("TH");countryList.add("TR");countryList.add("TG");
        countryList.add("TN");countryList.add("TT");countryList.add("PA");countryList.add("PY");countryList.add("PK");
        countryList.add("PG");countryList.add("PE");countryList.add("PT");countryList.add("PL");countryList.add("FR");
        countryList.add("FI");countryList.add("PH");countryList.add("HU");
        
    }
    
    public List<String> getCountryList() {
        return this.countryList;
    }
    
    public String getKRCountryName(String countryCode) {
        return krCountryMap.get(countryCode);
    }
    
    public String getENCountryName(String countryCode) {
        return enCountryMap.get(countryCode);
    }
    
    public static void main(String args[]) {
        
        try {
            CountryCodes cCodes = new CountryCodes();
//            System.out.println("KR : "+cCodes.getKRCountryName("KR")+" "+cCodes.getENCountryName("KR"));
//            System.out.println("CN : "+cCodes.getKRCountryName("CN")+" "+cCodes.getENCountryName("CN"));
//            System.out.println("VN : "+cCodes.getKRCountryName("VN")+" "+cCodes.getENCountryName("VN"));
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

/**
GH 가나 GHANA
GA 가봉 GABON
GY 가이아나 GUYANA
GM 감비아 GAMBIA
GP 프랑스(과데루프섬) GUADELOUPE
GT 과테말라 GUATEMALA
GU 미국(괌) GUAM
GD 그레나다 GRENADA
GE 그루지야 GEORGIA
GR 그리스 GREECE
GL 덴마크(그린란드) GREENLAND
GW 기니비소 GUINEA-BISSAU
GN 기니 GUINEA
NA 나미비아 NAMIBIA
NG 나이지리아 NIGERIA
ZA 남아프리카공화국 SOUTHAFRICA
NL 네덜란드(네델란드) NETHERLANDS
AN 네덜란드(네덜란드령앤틸리스) NETHERLANDS(ANTILLES)
NP 네팔 NEPAL
NO 노르웨이 NORWAY
NF 오스트레일리아(노퍽섬) NORFOLKISLAND
NZ 뉴질랜드 NEWZEALAND
NC 프랑스(뉴칼레도니아섬) NEWCALEDONIA
NE 니제르 NIGER
NI 니카라과 NICARAGUA
TW 타이완(대만) TAIWAN
DK 덴마크 DENMARK
DM 도미니카연방 DOMINICA
DO 도미니카공화국 DOMINICANREPUBLIC
DE 독일 GERMANY
LA 라오스 LAOPEOPLE'SDEMREP
LV 라트비아 LATVIA
RU 러시아 RUSSIANFEDERATION
LB 레바논 LEBANON
LS 레소토 LESOTHO
RO 루마니아 ROMANIA
RW 르완다 RWANDA
LU 룩셈부르크 LUXEMBOURE
LR 라이베리아 LIBERIA
LY 리비아 LIBYANARABJAMAHIRIYA
RE 프랑스(레위니옹섬) REUNION
LT 리투아니아 LITHUANIA
LI 리첸쉬테인 LIECHTENSTEIN
MG 마다가스카르 MADAGASCAR
MH 미국(마아샬제도) MARSHALLISLANDS
FM 미크로네시아(마이크로네시아) MICRONESIA
MK 마케도니아 MACEDONIA
MW 말라위 MALAWI
MY 말레이지아 MALAYSIA
ML 말리 MALI
MT 몰타 MALTA
MQ 프랑스(마르티니크섬) MARTINIQUE
MX 멕시코 MEXICO
MC 모나코 MONACO
MA 모로코 MOROCCO
MU 모리셔스 MAURITIUS
MR 모리타니 MAURITANIA
MZ 모잠비크 MOZAMBIQUE
MS 영국(몽세라) MONTSERRAT
MD 몰도바 MOLDOVA,REPUBLICOF
MV 몰디브 MALDIVES
MN 몽고 MONGOLIA
US 미국 U.S.A
VI 미국(버진제도) VIRGINISLANDSU.S.
AS 미국(사모아제도) AMERICANSAMOA
MM 미얀마 MYANMAR
VU 바누아투 VANUATU
BH 바레인 BAHRAIN
BB 바베이도스 BARBADOS
BS 바하마 BAHAMAS
BD 방글라데시 BANGLADESH
BY 벨라루스 BELARUS
BM 영국(버뮤다섬) BERMUDA
VE 베네수엘라 VENEZUELA
BJ 베넹 BENIN
VN 베트남 VIETNAM
BE 벨기에 BELGIUM
BZ 벨리세 BELIZE
BA 보스니아헤르체코비나 BosniaandHerzegovina
BW 보츠와나 BOTSWANA
BO 볼리비아 BOLIVIA
BF 부르키나파소 BURKINAFASO
BT 부탄 BHUTAN
MP 미국(사이판) NORTHERNMARIANAISLANDS
BG 불가리아 BULGARIA(REP)
BR 브라질 BRAZIL
BN 브루네이(나이) BRUNEIDARUSSALAM
BI 브룬디 BURUNDI
WS 미국(사모아, 구 서사모아) SAMOA
SA 사우디아라비아 SAUDIARABIA
CY 사이프러스 CYPRUS
SM 산마리노 SANMARINO
SN 세네갈 SENEGAL
SC 세이셸 SEYCHELLES
LC 세인트루시아 SAINTLUCIA
VC 세인트빈센트그레나딘 SAINTVINCENTANDTHEGRENADINES
KN 세인트키츠네비스 SAINTKITTSANDNEVIS
SB 솔로몬아일란드 SOLOMONISLANDS
SR 수리남 SURINAME
LK 스리랑카 SRILANKA
SZ 스와질랜드 SWAZILAND
SE 스웨덴 SWEDEN
CH 스위스 SWITZERLAND
ES 스페인(에스파니아) SPAIN
SK 슬로바키아 SLOVAKIA
SI 슬로베니아 SLOVENIA
SL 시에라리온 SIERRALEONE
SG 싱가포르 SINGAPORE
AE 아랍에미레이트연합국 UNITEDARABEMIRATES
AW 네덜란드(아루바섬) ARUBA
AM 아르메니아 ARMENIA
AR 아르헨티나 ARGENTINA
IS 아이슬란드 ICELAND
HT 아이티 HAITI
IE 아일란드(에이레) IRELAND
AZ 아제르바이잔 AZERBAIJAN
AF 아프가니스탄 AFGHANISTAN
AI 영국(안귈라섬) ANGUILLA
AD 안도라 ANDORRA
AG 앤티과바부다 ANTIGUAANDBARBUDA
AL 알바니아 ALBANIA
DZ 알제리 ALGERIA
AO 앙골라 ANGOLA
ER 에리트리아 ERITREA
EE 에스토니아 ESTONIA
EC 에콰도르 ECUADOR
SV 엘살바도르 ELSALVADOR
GB 영국 UNITEDKINGDOM
VG 영국(버진제도) VIRGINISLANDSBRITISH
YE 예멘 YEMEN
OM 오만 OMAN
AU 오스트레일리아(호주) AUSTRALIA
AT 오스트리아 AUSTRIA
HN 온두라스 HONDURAS
JO 요르단 JORDAN
UG 우간다 UGANDA
UY 우루과이 URUGUAY
UZ 우즈베크 UZBEKISTAN
UA 우크라이나 UKRAINE
ET 이디오피아 ETHIOPIA
IQ 이라크 IRAQ
IR 이란 IRAN(ISLAMICREP)
IL 이스라엘 ISRAEL
EG 이집트 EGYPT
IT 이탈리아(이태리) ITALY
IN 인도 INDIA
ID 인도네시아 INDONESIA
JP 일본 JAPAN
JM 자메이카 JAMAICA
ZM 잠비아 ZAMBIA
CN 중국 CHINA(PEOPLE'SREP)
MO 중국(마카오) MACAO
HK 중국(홍콩) CHINA(HONGKONG)
CF 중앙아프리카 CENTRALAFRICANREPUBLIC
DJ 지부티 DJIBOUTI
GI 영국(지브롤터) GIBRALTAR
ZW 짐바브웨 ZIMBABWE
TD 차드 CHAD
CZ 체코 CZECHREP
CS 체코슬로바키아 CZECHOSLOVAKIA
CL 칠레 CHILE
CA 카나다 CANADA
CM 카메룬 CAMEROON
CV 카보베르데 CAPEVERDE
KY 영국(케이만제도) CAYMANISLANDS
KZ 카자흐 KAZAKHSTAN
QA 카타르 QATAR
KH 캄보디아 CAMBODIA
KE 케냐 KENYA
CR 코스타리카 COSTARICA
CI 코트디봐르 COTEDIVOIRE
CO 콜롬비아 COLOMBIA
CG 콩고 CONGO
CU 쿠바 CUBA
KW 쿠웨이트 KUWAIT
HR 크로아티아 CROATIA
KG 키르키즈스탄 KYRGYZSTAN
KI 키리바티 KIRIBATI
TJ 타지키스탄 TAJIKISTAN
TZ 탄자니아 TANZANIA(UNITEDREP)
TH 타이(태국) THAILAND
TC 영국(터크스케이코스제도) TURKSANDCAICOSISLANDS
TR 터키 TURKEY
TG 토고 TOGO
TO 통가 TONGA
TV 투발루 TUVALU
TN 튀니지 TUNISIA
TT 트리니다드토바고 TRINIDADANDTOBAGO
PA 파나마 PANAMA(REP)
PY 파라과이 PARAGUAY
PK 파키스탄 PAKISTAN
PG 파푸아뉴기니 PAPUANEWGUINEA
PW 미국(팔라우섬) PALAU
FO 덴마크(페로즈제도) FAROEISLANDS
PE 페루 PERU
PT 포르투갈 PORTUGAL
PL 폴란드 POLAND(REP)
PR 미국(푸에르토리코섬) PUERTORICO
FR 프랑스 FRANCE
GF 프랑스(기아나) FRENCHGUIANA
PF 프랑스(폴리네시아) FRENCHPOLYNESIA
FJ 피지 FIJI
FI 필란드 FINLAND
PH 필리핀 PHILIPPINES
HU 헝가리 HUNGARY(REP)
*/


