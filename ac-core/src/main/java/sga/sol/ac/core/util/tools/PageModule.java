package sga.sol.ac.core.util.tools;

/**
 * 페이징 처리 BEAN
 * CommualModule에서 상속받아 사용하며 모든 DTO 객체의 부모 객체
 * 
 * @author JannyWang
 * - 2015-01-21(surfree): 중국어 주석 수정
 * TODO: 이 객체를 DTO에서 분리해야 함
 */

public class PageModule {

	private int startPage;						// 시작
	private int endPage;						// 끝
	private int count;							// 개수
	
	private String sortFieldNm;
	private String sortDir;	
	
	private int exInt1;
	private int exInt2;
	private int exInt3;	
	
	private String exStr1;
	private String exStr2;
	private String exStr3;
	
	
	public String getSortFieldNm() {
		return sortFieldNm;
	}

	public void setSortFieldNm(String sortFieldNm) {
		this.sortFieldNm = sortFieldNm;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}

	public String getExStr1() {
		return exStr1;
	}

	public void setExStr1(String exStr1) {
		this.exStr1 = exStr1;
	}

	public String getExStr2() {
		return exStr2;
	}

	public void setExStr2(String exStr2) {
		this.exStr2 = exStr2;
	}

	public String getExStr3() {
		return exStr3;
	}

	public void setExStr3(String exStr3) {
		this.exStr3 = exStr3;
	}
}
