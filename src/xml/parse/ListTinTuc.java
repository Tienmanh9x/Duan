package xml.parse;

import java.util.ArrayList;
import java.util.List;

public class ListTinTuc {
	
	private List<TinTuc> listTinTuc = new ArrayList<TinTuc>();
	
	public List<TinTuc> getListTinTuc(){
		return listTinTuc;
	}
	
	public void setListTinTuc(TinTuc _tintuc){
		this.listTinTuc.add(_tintuc);
	}
	
	public ListTinTuc listTinTucCopy(){
		ListTinTuc copy = new ListTinTuc();
		copy.listTinTuc = listTinTuc;
		return copy;
	}
}
