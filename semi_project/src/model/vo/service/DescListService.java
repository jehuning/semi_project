package model.vo.service;

import java.util.Comparator;

import model.vo.dao.FriendsDao;

public class DescListService implements Comparator<FriendsDao>{
	public int compare(FriendsDao a, FriendsDao b)
	{
		return b.getFriendMsgDate().compareTo(a.getFriendMsgDate());
	}
}
