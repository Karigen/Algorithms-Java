package search;

//import java.util.Arrays;

//����������ֵ����
//ע�⣺�����е�ֵ�ֲ����ȣ���ֵ�����ٶȿ죬�����е�ֵ�ֲ������ȣ���ֵ�����ٶȲ�һ���ȶ��ֲ��ҿ�
public class InsertValueSearch {

    public static void main(String[] args) {
	
	/*
	
	int[] arr=new int[100];
	for (int i = 0; i < arr.length; i++) {
	    arr[i]=i+1;
	}
	
	*/
	
	int[] arr= {1, 8, 10, 89, 1000, 1000, 1234};
	
	int index=insertValueSearch(arr, 0, arr.length-1, 1234);
	//int index=binarySearch1(arr, 0, arr.length-1, 1);
	
	System.out.println("index="+index);
	
	//System.out.println(Arrays.toString(arr));
    }
    
    public static int binarySearch1(int[] arr, int left, int right, int findValue) {
	
	System.out.println("���ֲ��ұ�����");
	
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
    
    //��д��ֵ�����㷨
    //˵������ֵ�����㷨��ҲҪ������ֵ�����
    /**
     * 
     * @param arr ����
     * @param left �������
     * @param right �ұ�����
     * @param findValue ����ֵ
     * @return ����ҵ����ͷ��ض�Ӧ�±꣬���û���ҵ�������-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
	
	System.out.println("��ֵ���Ҵ���");
	
	//ע�⣺findValue<arr[0]��findValue>arr[arr.length-1]������Ҫ
	//�������ǵõ���mid����Խ��
	if (left>right||findValue<arr[0]||findValue>arr[arr.length-1]) {
	    return-1;
	}
	
	//���mid������Ӧ
	int mid=left+(right-left)*(findValue-arr[left])/(arr[right]-arr[left]);
	int midValue=arr[mid];
	if (findValue>midValue) {//˵��Ӧ�����ұߵݹ�
	    return insertValueSearch(arr, mid+1, right, findValue);
	} else if (findValue<midValue) {//˵������ݹ����
	    return insertValueSearch(arr, left, mid-1, findValue);
	} else {
	    return mid;
	}
    }

}
