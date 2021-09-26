package search;

import java.util.Arrays;

//�����������
public class FibonacciSearch {
    
    public static int maxSize=20;

    public static void main(String[] args) {
	int[] arr= {1, 8, 10, 89, 1000, 1234}; 
	
	System.out.println("index="+fibSearch(arr, 189));//0
    }
    
    //��Ϊ�������mid=low+F(k-1)-1����Ҫʹ��쳲��������У������ô��Ҫ�Ȼ�ȡ��쳲���������
    //�ǵݹ�ķ�ʽ�õ�һ��쳲���������
    public static int[] fib() {
	int[] f=new int[maxSize];
	f[0]=1;
	f[1]=1;
	for (int i = 2; i < f.length; i++) {
	    f[i]=f[i-1]+f[i-2];
	}
	return f;
    }
    
    //��д쳲����������㷨
    //ʹ�÷ǵݹ�ķ�ʽ��д�㷨
    /**
     * 
     * @param a ����
     * @param key ������Ҫ���ҵĹؼ��루ֵ��
     * @return ���ض�Ӧ���±꣬���û��-1
     */
    public static int fibSearch(int[] a, int key) {
	int low=0;
	int high=a.length-1;
	int k=0;//��ʾ쳲������ָ���ֵ���±�
	int mid=0;//���midֵ
	int f[]=fib();//��ȡ��쳲���������
	
	//��ȡ��쳲������ָ���ֵ���±�
	while (high>f[k]-1) {
	    k++;
	}
	
	//��Ϊf[k]ֵ���ܴ���a�ĳ��ȣ����������Ҫʹ��Arrays�࣬����һ���µ����飬��ָ��temp[]
	//����Ĳ��ֻ�ʹ��0���
	int[] temp=Arrays.copyOf(a, f[k]);
	//ʵ������Ҫʹ��a�����������ֵ���temp
	//������
	//temp={1, 8, 10, 89, 1000, 1234, 0, 0}=>{1, 8, 10, 89, 1000, 1234, 1234, 1234}
	for (int i = high+1; i < temp.length; i++) {
	    temp[i]=a[high];
	}
	
	//ʹ��while��ѭ���������������ǵ���key
	while (low<=high) {//ֻҪ����������㣬�Ϳ�����
	    mid=low+f[k-1]-1;
	    if (key<temp[mid]) {//����Ӧ�ü����������ǰ����ң���ߣ�
		high=mid-1;
		//Ϊʲô��k--
		//˵��
		//1.ȫ��Ԫ��=ǰ���Ԫ��+��ߵ�Ԫ��
		//2.f[k]=f[k-1]+f[k-2]
		//��Ϊǰ����f[k-1]��Ԫ�أ����Կ��Լ������ f[k-1]=f[k-2]+f[k-3]
		//����f[k-1]��ǰ���������k--
		//���´�ѭ��mid=f[k-1-1]-1
		k--;
	    } else if (key>temp[mid]) {//����Ӧ�ü���������ĺ�����ң��ұߣ�
		low=mid+1;
		//Ϊʲô��k-=2
		//˵��
		//1. ȫ��Ԫ��=ǰ���Ԫ��+��ߵ�Ԫ��
		//2.f[k]=f[k-1]+f[k-2]
		//3.��Ϊ����������f[k-2]���Կ��Լ������ f[k-1]=f[k-3]+f[k-4]
		//4.����f[k-2]��ǰ����в���k-=2
		//5.���´�ѭ��mid=f[k-1-2]-1
		k-=2;
	    } else {//�ҵ�
		//��Ҫȷ�������ص����ĸ��±�
		if (mid<=high) {
		    return mid;
		} else {
		    return high;
		}
	    }
	}
	return-1;
    }

}