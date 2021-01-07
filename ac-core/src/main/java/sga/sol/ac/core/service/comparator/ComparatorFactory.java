package sga.sol.ac.core.service.comparator;

import java.util.Collections;
import java.util.Comparator;

import sga.sol.ac.core.entity.equip.EquipAccount;
import sga.sol.ac.core.entity.equip.EquipGroupInfo;
import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserInfo;

public class ComparatorFactory {
	public enum SortOrder {
		SORT_ASC("ASC"),
		SORT_DESC("DESC");
		
		final String str;
		
		private SortOrder(String str) {
			this.str = str;
		}
		
		public String valueOf() {
			return this.str;
		}
		
		public static SortOrder getSort(String sortString) {
			if(sortString.equalsIgnoreCase("DESC"))
				return SortOrder.SORT_DESC;
			else
				return SortOrder.SORT_ASC;
		}
	};
	
	public static Comparator<UserInfo> getUserIdComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<UserInfo>() {
				@Override
				public int compare(UserInfo o1, UserInfo o2) {
					return o2.getUserId().compareTo(o1.getUserId());
					
				}
			};
		default:
			return new Comparator<UserInfo>() {
				@Override
				public int compare(UserInfo o1, UserInfo o2) {
					return o1.getUserId().compareTo(o2.getUserId());
					
				}
			};
		}
	}
	
	public static Comparator<UserInfo> getUserIdComparator(String sort) {
		SortOrder sortOrder = SortOrder.getSort(sort);
		return getUserIdComparator(sortOrder);
	}
	
	public static Comparator<UserInfo> getUserNameComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<UserInfo>() {
				@Override
				public int compare(UserInfo o1, UserInfo o2) {
					o1.setUserName((o1.getUserName()==null) ? "" : o1.getUserName());
					o2.setUserName((o2.getUserName()==null) ? "" : o2.getUserName());
					return o1.getUserName().compareTo(o2.getUserName());
					
				}
			};
		default:
			return new Comparator<UserInfo>() {
				@Override
				public int compare(UserInfo o1, UserInfo o2) {
					o1.setUserName((o1.getUserName()==null) ? "" : o1.getUserName());
					o2.setUserName((o2.getUserName()==null) ? "" : o2.getUserName());
					return o2.getUserName().compareTo(o1.getUserName());
					
				}
			};
		}
	}
	
	public static Comparator<UserInfo> getUserNameComparator(String sort) {
		SortOrder sortOrder = SortOrder.getSort(sort);
		return getUserNameComparator(sortOrder);
	}
	
	public static Comparator<UserInfo> getUserAssocGroupPathComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<UserInfo>() {
				@Override
				public int compare(UserInfo o1, UserInfo o2) {
					return o2.getUserGroup().getGroupPath().compareTo(o1.getUserGroup().getGroupPath());
				}
			};
		default:
			return new Comparator<UserInfo>() {
				@Override
				public int compare(UserInfo o1, UserInfo o2) {
					return o1.getUserGroup().getGroupPath().compareTo(o2.getUserGroup().getGroupPath());
				}
			};
		}
	}
	
	public static Comparator<UserInfo> getUserAssocGroupPathComparator(String sort) {
		SortOrder sortOrder = SortOrder.getSort(sort);
		return getUserAssocGroupPathComparator(sortOrder);
	}
	
	public static Comparator<UserGroupInfo> getUserGroupNameComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<UserGroupInfo>() {
				@Override
				public int compare(UserGroupInfo o1, UserGroupInfo o2) {
					return o2.getGroupName().compareTo(o1.getGroupName());
				}
			};
		default:
			return new Comparator<UserGroupInfo>() {
				@Override
				public int compare(UserGroupInfo o1, UserGroupInfo o2) {
					return o1.getGroupName().compareTo(o2.getGroupName());
				}
			};
		}
	}
	
	public static Comparator<UserGroupInfo> getUserGroupNameComparator(String sort) {
		SortOrder sortOrder = SortOrder.getSort(sort);
		return getUserGroupNameComparator(sortOrder);
	}
	
	public static Comparator<UserGroupInfo> getUserGroupPathComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<UserGroupInfo>() {
				@Override
				public int compare(UserGroupInfo o1, UserGroupInfo o2) {
					return o2.getGroupPath().compareTo(o1.getGroupPath());
					
				}
			};
		default:
			return new Comparator<UserGroupInfo>() {
				@Override
				public int compare(UserGroupInfo o1, UserGroupInfo o2) {
					return o1.getGroupPath().compareTo(o2.getGroupPath());
					
				}
			};
		}
	}
	
	public static Comparator<UserGroupInfo> getUserGroupPathComparator(String sort) {
		SortOrder sortOrder = SortOrder.getSort(sort);
		return getUserGroupPathComparator(sortOrder);
	}
	
	public static Comparator<EquipGroupInfo> getEquipGroupNameComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<EquipGroupInfo>() {
				@Override
				public int compare(EquipGroupInfo o1, EquipGroupInfo o2) {
					return o2.getGroupName().compareTo(o1.getGroupName());
					
				}
			};
		default:
			return new Comparator<EquipGroupInfo>() {
				@Override
				public int compare(EquipGroupInfo o1, EquipGroupInfo o2) {
					return o1.getGroupName().compareTo(o2.getGroupName());
					
				}
			};
		}
	}
	
	public static Comparator<EquipGroupInfo> getEquipGroupNameComparator(String sort) {
		SortOrder sortOrder = SortOrder.getSort(sort);
		return getEquipGroupNameComparator(sortOrder);
	}
	
	public static Comparator<EquipGroupInfo> getEquipGroupPathComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<EquipGroupInfo>() {
				@Override
				public int compare(EquipGroupInfo o1, EquipGroupInfo o2) {
					return o2.getGroupPath().compareTo(o1.getGroupPath());
					
				}
			};
		default:
			return new Comparator<EquipGroupInfo>() {
				@Override
				public int compare(EquipGroupInfo o1, EquipGroupInfo o2) {
					return o1.getGroupPath().compareTo(o2.getGroupPath());
					
				}
			};
		}
	}
	
	public static Comparator<EquipGroupInfo> getEquipGroupPathComparator(String sort) {
		SortOrder sortOrder = SortOrder.getSort(sort);
		return getEquipGroupPathComparator(sortOrder);
	}
	
	public static Comparator<EquipAccount> getEquipAccountAssocEquipComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<EquipAccount>() {
				@Override
				public int compare(EquipAccount o1, EquipAccount o2) {
					return o2.getEquipInfo().getEquipName().compareTo(o1.getEquipInfo().getEquipName());
					
				}
			};
		default:
			return new Comparator<EquipAccount>() {
				@Override
				public int compare(EquipAccount o1, EquipAccount o2) {
					return o1.getEquipInfo().getEquipName().compareTo(o2.getEquipInfo().getEquipName());
				}
			};
		}
	}
	
	public static Comparator<EquipAccount> getEquipAccountNameComparator(SortOrder sortOrder) {
		switch(sortOrder) {
		case SORT_DESC:
			return new Comparator<EquipAccount>() {
				@Override
				public int compare(EquipAccount o1, EquipAccount o2) {
					return o2.getAccount().compareTo(o1.getAccount());
					
				}
			};
		default:
			return new Comparator<EquipAccount>() {
				@Override
				public int compare(EquipAccount o1, EquipAccount o2) {
					return o1.getAccount().compareTo(o2.getAccount());
				}
			};
		}
	}

}
