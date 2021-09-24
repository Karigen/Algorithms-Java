package search;

/*
 * ˼·����
 * 1.����ȷ����������м���±�
 *  mid=(left+right)/2
 * 2.Ȼ������Ҫ���ҵ���findValue��arr[mid]�Ƚ�
 *  2.1 findValue>midValue��˵����Ҫ���ҵ�����mid���ұߣ������Ҫ�ݹ�����Ҳ���
 *  2.2 findValue<midValue��˵����Ҫ���ҵ�����mid����ߣ������Ҫ�ݹ���������
 *  2.3 findValue==midValue��˵���ҵ����ͷ���
 * 3.ʲôʱ������ݹ�
 *  3.1�ҵ��ͽ����ݹ�
 *  3.2�ݹ��������飬��Ȼû���ҵ�findValue��Ҳ��Ҫ�����ݹ飬��left>right����Ҫ�˳�
 */

import java.util.ArrayList;
import java.util.List;

//ע�⣺ʹ�ö��ֲ��ҵ�ǰ���Ǹ�����������ġ�
public class BinarySearch {

    public static void main(String[] args) {
	int[] arr= {1, 8, 10, 89, 1000, 1000, 1234}; 
	
	/*
	
	int resultIndex=binarySearch(arr, 0, arr.length-1, 1000);
	System.out.println("resultIndex="+resultIndex);
	
	*/
	
	List<Integer> resultIndexList=binarySearch2(arr, 0, arr.length-1, 1000);
	System.out.println("resultIndexList="+resultIndexList);
    }
    
    //���ֲ����㷨
    /**
     * 
     * @param arr ����
     * @param left ��ߵ�����
     * @param right �ұߵ�����
     * @param value Ҫ���ҵ�ֵ
     * @return ����ҵ��ͷ����±꣬���û���ҵ����ͷ���-1
     */
    public static int binarySearch1(int[] arr, int left, int right, int findValue) {
	
	//��left>rightʱ��˵���ݹ��������飬����û���ҵ�
	if (left>right) {
	    return-1;
	}
	
	int mid=(left+right)/2;
	int midValue=arr[mid];
	
	if (findValue>midValue) {// ���ҵݹ�
	    return binarySearch1(arr, mid+1, right, findValue);
	} else if (findValue<midValue) {//����ݹ�
	    return binarySearch1(arr, left, mid-1, findValue);
	} else {
	    return mid;
	}
    }
    
    //���һ���κ�˼���⣺
    /*
     * �κ�˼���⣺{1, 8, 10, 89, 1000, 1000, 1234} ��һ�����������У�
     * �ж����ͬ����ֵʱ����ν����е���ֵ�����ҵ������������1000
     * 
     * ˼·����
     * 1.���ҵ�mid����ֵʱ����Ҫ���Ϸ���
     * 2.��mid����ֵ�����ɨ�裬����������1000����Ԫ�ص��±꣬���뵽����ArrayList
     * 3.��mid����ֵ���ұ�ɨ�裬����������1000����Ԫ�ص��±꣬���뵽����ArrayList
     * 4.��ArrayList����
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {
	
	//��left>rightʱ��˵���ݹ��������飬����û���ҵ�
	if (left>right) {
	    return new ArrayList<Integer>();
	}
	
	int mid=(left+right)/2;
	int midValue=arr[mid];
	
	if (findValue>midValue) {// ���ҵݹ�
	    return binarySearch2(arr, mid+1, right, findValue);
	} else if (findValue<midValue) {//����ݹ�
	    return binarySearch2(arr, left, mid-1, findValue);
	} else {
	    /* ˼·����
	     * 1.���ҵ�mid����ֵʱ����Ҫ���Ϸ���
	     * 2.��mid����ֵ�����ɨ�裬����������1000����Ԫ�ص��±꣬���뵽����ArrayList
	     * 3.��mid����ֵ���ұ�ɨ�裬����������1000����Ԫ�ص��±꣬���뵽����ArrayList
	     * 4.��ArrayList����
	     */
	    List<Integer> resultIndexList=new ArrayList<Integer>();
	    //��mid����ֵ�����ɨ�裬����������1000����Ԫ�ص��±꣬���뵽����ArrayList
	    int temp=mid-1;
	    while (true) {
		if (temp<0||arr[temp]!=findValue) {//�˳�
		    break;
		}
		//���򣬾Ͱ�temp���뵽resultIndexList��
		resultIndexList.add(temp);
		temp-=1;//temp����
	    }
	    resultIndexList.add(mid);//
	    
	    //��mid����ֵ���ұ�ɨ�裬����������1000����Ԫ�ص��±꣬���뵽����ArrayList
	    temp=mid+1;
	    while (true) {
		if (temp>arr.length-1||arr[temp]!=findValue) {//�˳�
		    break;
		}
		//���򣬾Ͱ�temp���뵽resultIndexList��
		resultIndexList.add(temp);
		temp+=1;//temp����
	    }
	    
	    return resultIndexList;
	    
	}
    }

}
