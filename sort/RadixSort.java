package sort;

/*
 *��������Ŀǰ��֧�ָ�����
 *1.��1������ 
 *  1.1 ��ÿ��Ԫ�صĸ�λ��ȡ����Ȼ�������Ӧ�÷����ĸ���Ӧ��Ͱ��һ��һά���飩
 *  1.2 �������Ͱ��˳��һά������±�����ȡ�����ݣ�����ԭ�������飩
 *  
 *2.��2������
 *  2.1 ��ÿ��Ԫ�ص�ʮλ��ȡ����Ȼ�������Ӧ�÷����ĸ���Ӧ��Ͱ��һ��һά���飩
 *  2.2 �������Ͱ��˳��һά������±�����ȡ�����ݣ�����ԭ�������飩
 *  
 *������
 *
 *�ȶ��ԣ�
 *�ٶ��ڴ�����ļ�¼�����У����ڶ��������ͬ�Ĺؼ��ֵļ�¼��������������Щ��¼����Դ��򱣳ֲ��䣬
 *����ԭ�����У�r[i]==r[j]����r[i]��r[j]֮ǰ�����������������У�r[i]����r[j]֮ǰ����������㷨���ȶ��ģ������Ϊ���ȶ��ġ�
 *
 *������
 *����������������ڴ������
 *
 *������
 *��������̫����˰����ݷ��ڴ����У�������ͨ�����̺��ڴ�����ݴ�����ܽ���
 *
 *In-place��
 *��ռ�ö����ڴ�
 *
 *Out-place��
 *ռ�ö����ڴ�
 */

//import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RadixSort {

    public static void main(String[] args) {
	//int[] arr={53, 3, 542, 748, 14, 214};
	
	//����Ҫ��80000�������������
	int[]arr=new int[80000];
	for (int i = 0; i < 80000; i++) {
	    arr[i]=(int)(Math.random()*8000000);//����һ��[0,8000000)��
	}
	
	Date date1=new Date();
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date1Str=simpleDateFormat.format(date1);
	System.out.println("����ǰ��ʱ��="+date1Str);
	
	radixSort(arr);
	
	Date date2=new Date();
	String date2Str=simpleDateFormat.format(date2);
	System.out.println("������ʱ��="+date2Str);
    }
    
    //�������򷽷�
    public static void radixSort(int[] arr) {
	
	//����ǰ����Ƶ����̣����ǿ��Եõ����յĻ����������
	
	//1.�õ���������������λ��
	int max=arr[0];//�����һ�������������
	for (int i = 1; i < arr.length; i++) {
	    if(arr[i]>max) {
		max=arr[i];
	    }
	}
	
	//�õ�������Ǽ�λ��
	int maxLength=(max+"").length();
	
	//����һ����ά���飬��ʾ10��Ͱ��ÿ��Ͱ����һά����
	//˵��
	//1. ��ά�������10��һά����
	//2. Ϊ�˷�ֹ�ڷ�������ʱ�������������ÿ��һά���飨Ͱ������СΪarr.length
	//3. ��ȷ������������ʹ�ÿռ任ʱ��ľ����㷨
	int[][] bucket=new int[10][arr.length];
	
	//Ϊ�˼�¼ÿ��Ͱ�У�ʵ�ʴ���˶��ٸ����ݣ����Ƕ���һ��һά��������¼����Ͱÿ�η�������ݵĸ���
	//�����������
	//���磺bucketElementCounts[0]����¼�ľ���bucke[0]Ͱ�ķ�������ݸ���
	int[] bucketElementCounts=new int[10]; 
	
	//��������ʹ��ѭ�������봦��
	for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
	    //���ÿ��Ԫ�صĶ�Ӧλ������������һ���Ǹ�λ���ڶ�����ʮλ���������ǰ�λ������
	    for (int j = 0; j < arr.length; j++) {
	    //ȡ��ÿ��Ԫ�صĶ�Ӧλ��ֵ
	    int digitOfElement=arr[j]/n%10;
	    
	    //���뵽��Ӧ��Ͱ
	    bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
	    bucketElementCounts[digitOfElement]++;
	    }
	
	    //�������Ͱ��˳��һά������±�����ȡ�����ݣ�����ԭ�������飩
	    int index=0;
	    
	    //����ÿһ��Ͱ������Ͱ�����ݣ����뵽ԭ����
	    for (int k = 0; k < bucketElementCounts.length; k++) {
		//���Ͱ�У������ݣ����ǲŷ��뵽ԭ����
		if (bucketElementCounts[k]!=0) {
		    //ѭ����Ͱ����k��Ͱ������k��һά���飩������
		    for (int l = 0; l < bucketElementCounts[k]; l++) {
			//ȡ��Ԫ�ط��뵽arr
			arr[index++]=bucket[k][l];
		    }
		}
	    
	    //��i+1�ִ������Ҫ��ÿ��bucketElementCounts[k]=0������
	    bucketElementCounts[k]=0;
	    
	    }
	
	//System.out.println("��"+(i+1)+"�֣��Ը�λ�������� arr = "+Arrays.toString(arr));
	}
	
	/*
	
	//��һ�֣����ÿ��Ԫ�صĸ�λ����������
	for (int j = 0; j < arr.length; j++) {
	    //ȡ��ÿ��Ԫ�صĸ�λ��ֵ
	    int digitOfElement=arr[j]/1%10;
	    
	    //���뵽��Ӧ��Ͱ
	    bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
	    bucketElementCounts[digitOfElement]++;
	}
	
	//�������Ͱ��˳��һά������±�����ȡ�����ݣ�����ԭ�������飩
	int index=0;
	
	//����ÿһ��Ͱ������Ͱ�����ݣ����뵽ԭ����
	for (int k = 0; k < bucketElementCounts.length; k++) {
	    //���Ͱ�У������ݣ����ǲŷ��뵽ԭ����
	    if (bucketElementCounts[k]!=0) {
		//ѭ����Ͱ����k��Ͱ������k��һά���飩������
		for (int l = 0; l < bucketElementCounts[k]; l++) {
		    //ȡ��Ԫ�ط��뵽arr
		    arr[index++]=bucket[k][l];
		}
	    }
	    
	    //��1�ִ������Ҫ��ÿ��bucketElementCounts[k]=0������
	    bucketElementCounts[k]=0;
	    
	}
	
	System.out.println("��1�֣��Ը�λ�������� arr = "+Arrays.toString(arr));
	
	//�ڶ��֣����ÿ��Ԫ�صİ�λ����������
	for (int j = 0; j < arr.length; j++) {
	    //ȡ��ÿ��Ԫ�ص�ʮλ��ֵ
	    int digitOfElement=arr[j]/10%10;
	    
	    //���뵽��Ӧ��Ͱ
	    bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
	    bucketElementCounts[digitOfElement]++;
	}
	
	//�������Ͱ��˳��һά������±�����ȡ�����ݣ�����ԭ�������飩
	index=0;
	
	//����ÿһ��Ͱ������Ͱ�����ݣ����뵽ԭ����
	for (int k = 0; k < bucketElementCounts.length; k++) {
	    //���Ͱ�У������ݣ����ǲŷ��뵽ԭ����
	    if (bucketElementCounts[k]!=0) {
		//ѭ����Ͱ����k��Ͱ������k��һά���飩������
		for (int l = 0; l < bucketElementCounts[k]; l++) {
		    //ȡ��Ԫ�ط��뵽arr
		    arr[index++]=bucket[k][l];
		}
	    }
	    //��2�ִ������Ҫ��ÿ��bucketElementCounts[k]=0������
	    bucketElementCounts[k]=0;
	}
	
	System.out.println("��2�֣���ʮλ�������� arr = "+Arrays.toString(arr));
	
	//�����֣����ÿ��Ԫ�صİ�λ����������
	for (int j = 0; j < arr.length; j++) {
	    //ȡ��ÿ��Ԫ�صİ�λ��ֵ
	    int digitOfElement=arr[j]/100%10;
	    
	    //���뵽��Ӧ��Ͱ
	    bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
	    bucketElementCounts[digitOfElement]++;
	}
	
	//�������Ͱ��˳��һά������±�����ȡ�����ݣ�����ԭ�������飩
	index=0;
	
	//����ÿһ��Ͱ������Ͱ�����ݣ����뵽ԭ����
	for (int k = 0; k < bucketElementCounts.length; k++) {
	    //���Ͱ�У������ݣ����ǲŷ��뵽ԭ����
	    if (bucketElementCounts[k]!=0) {
		//ѭ����Ͱ����k��Ͱ������k��һά���飩������
		for (int l = 0; l < bucketElementCounts[k]; l++) {
		    //ȡ��Ԫ�ط��뵽arr
		    arr[index++]=bucket[k][l];
		}
	    }
	    //��3�ִ������Ҫ��ÿ��bucketElementCounts[k]=0������
	    bucketElementCounts[k]=0;
	}
	
	System.out.println("��3�֣��԰�λ�������� arr = "+Arrays.toString(arr));
	
	*/
	
    }

}