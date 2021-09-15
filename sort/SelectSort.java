package sort;

/**
 *˵��
 *1.ѡ������һ���������С-1������
 *2.ÿ1����������һ��ѭ����ѭ���Ĺ��򣨴��룩
 * 2.1�ȼٶ���ǰ���������С��
 * 2.2Ȼ��ͺ����ÿ�������бȽϣ���������бȵ�ǰ����С������������ȷ����С�������õ��±�
 * 2.3����������������ʱ���͵õ�������С�����±�
 * 2.4����������ʵ�֣�
 */

//import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

//ѡ������

public class SelectSort {

    public static void main(String[] args) {
	//int[] arr= {101, 34, 119, 1, -1, 90, 123};
	
	//����һ��ѡ��������ٶ�O(n^2),��80000�����ݣ�����
	//����Ҫ��80000�������������
	int[]arr=new int[80000];
	for (int i = 0; i < 80000; i++) {
	    arr[i]=(int)(Math.random()*8000000);//����һ��[0,8000000)��
	}

	//System.out.println("����ǰ");
	//System.out.println(Arrays.toString(arr));
	
	Date date1=new Date();
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date1Str=simpleDateFormat.format(date1);
	System.out.println("����ǰ��ʱ��="+date1Str);
	
	selectSort(arr);
	
	//System.out.println("�����");
	//System.out.println(Arrays.toString(arr));
	
	Date date2=new Date();
	String date2Str=simpleDateFormat.format(date2);
	System.out.println("������ʱ��="+date2Str);
    }

    //ѡ������
    public static void selectSort(int[] arr) {
	
	//���Ƶ������У����Ƿ����˹��ɣ���ˣ�����ʹ��for�����
	//ѡ�������ʱ�临�Ӷ���O(n^2)
	for (int i = 0; i < arr.length-1; i++) {
	    int minIndex=i;
	    int min=arr[i];
	    for (int j = i+1; j < arr.length; j++) {
		if (min>arr[j]) {//˵���ٶ�����Сֵ����������С
		    min=arr[j];//����min
		    minIndex=j;//����minIndex
		}
	    }
	    //����Сֵ������arr[i]��������
	    if (minIndex!=i) {
		arr[minIndex]=arr[i];
		arr[i]=min;
	    }
	
	    //System.out.println("��"+(i+1)+"�ֺ�");
	    //System.out.println(Arrays.toString(arr));//1�� 34�� 119�� 101
	}
	
	/*
	
	//ʹ�����Ƶ��ķ�ʽ��������ѡ������
	//��1��
	//ԭʼ�����飺 101�� 34�� 119�� 1
	//��һ������ 1�� 34�� 119�� 101
	//�㷨 �ȼ� -> �ٸ��ӣ����ǿ��԰�һ�����ӵ��㷨��ֳɼ򵥵�����->�𲽽��
	
	//��1��
	int minIndex=0;
	int min=arr[0];
	for (int j = 0+1; j < arr.length; j++) {
	    if (min>arr[j]) {//˵���ٶ�����Сֵ����������С
		min=arr[j];//����min
		minIndex=j;//����minIndex
	    }
	}
	//����Сֵ������arr[0]��������
	if (minIndex!=0) {
	    arr[minIndex]=arr[0];
	    arr[0]=min;
	}
	
	System.out.println("��һ�ֺ�");
	System.out.println(Arrays.toString(arr));//1�� 34�� 119�� 101
	
	//��2��
	minIndex=1;
	min=arr[1];
	for (int j = 1+1; j < arr.length; j++) {
	    if (min>arr[j]) {//˵���ٶ�����Сֵ����������С
		min=arr[j];//����min
		minIndex=j;//����minIndex
	    }
	}
	//����Сֵ������arr[1]��������
	if (minIndex!=1) {
	    arr[minIndex]=arr[1];
	    arr[1]=min;
	}
	
	System.out.println("�ڶ��ֺ�");
	System.out.println(Arrays.toString(arr));//1�� 34�� 119�� 101
	
	//��3��
	minIndex=2;
	min=arr[2];
	for (int j = 2+1; j < arr.length; j++) {
	    if (min>arr[j]) {//˵���ٶ�����Сֵ����������С
		min=arr[j];//����min
		minIndex=j;//����minIndex
	    }
	}
	//����Сֵ������arr[2]��������
	if (minIndex!=2) {
	    arr[minIndex]=arr[2];
	    arr[2]=min;
	}
	
	System.out.println("�����ֺ�");
	System.out.println(Arrays.toString(arr));//1�� 34�� 101�� 119
	
	*/
    }
    
}
