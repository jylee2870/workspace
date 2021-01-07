package sga.sol.ac.core;

/**
 * AuthCastle Core module 상수 정의
 * 
 * @author surfree
 * @version 1.0
 */
public class Constants {
	/** 성공 코드 */
	public static final int SUCCESS = 0;
	/** 실패 코드 */
	public static final int ERROR = 1;
	
	
	public static final int USER_LEVEL_MIN = 1;
	public static final int USER_LEVEL_MAX = 4;
	
	/** 최고관리자 사용자 레벨 */
	public static final int USER_LEVEL_SUPER = 1;
	/** 관리자 사용자 레벨 */
	public static final int USER_LEVEL_MANAGE = 2;
	/** 일반사용자 사용자 레벨 */
	public static final int USER_LEVEL_USER = 3;
	/** ARS 승인자 사용자 레벨 */
	public static final int USER_LEVEL_USER_ARS = 4;
}
