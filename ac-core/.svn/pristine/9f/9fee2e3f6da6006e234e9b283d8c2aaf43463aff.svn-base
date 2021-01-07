package sga.sol.ac.core.entity.criteria;

import java.util.HashMap;

/**
 * 항목 조회 시 필요한 검색 조건 객체
 * 
 * <ul>
 * <li>entity: 기본 객체</li>
 * <li>page: 페이지 객체</li>
 * <li>sort: 정렬 객체</li>
 * <li>extraParams: 추가 검색 조건 객체</li>
 * </ul>
 * 
 * @author surfree
 *
 */
public class SearchParams extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	Object entity;
	PageParam page;
	SortParam sort;
	ExtraParams extraParams;

	public SearchParams() {
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
		super.put("entity", entity);
	}

	public PageParam getPage() {
		return page;
	}

	public void setPage(PageParam page) {
		this.page = page;
		super.put("page", page);
	}

	public SortParam getSort() {
		return sort;
	}

	public void setSort(SortParam sort) {
		this.sort = sort;
		super.put("sort", this.sort);
	}

	public ExtraParams getExtraParams() {
		return extraParams;
	}

	public void setExtraParams(ExtraParams extraParams) {
		if(extraParams.isEmpty()) {
			// no extraParams is not allowed
			return;
		}
		
		this.extraParams = extraParams;
		super.put("extraParams", this.extraParams);
	}

	@Override
	public Object put(String key, Object value) {
		if( "entity".equalsIgnoreCase(key) ||
			"page".equalsIgnoreCase(key) ||
			"sort".equalsIgnoreCase(key) ||
			"extraParams".equalsIgnoreCase(key)) {
			throw new IllegalArgumentException("input key is not allowed " + key);
		}
		
		return super.put(key, value);
	}
}
