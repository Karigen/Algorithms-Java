package sort;

/**
 *说明
 *1.选择排序一共有数组大小-1轮排序
 *2.每1轮排序，又是一个循环，循环的规则（代码）
 * 2.1先假定当前这个数是最小数
 * 2.2然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
 * 2.3当遍历到数组的最后时，就得到本轮最小数和下标
 * 2.4交换（代码实现）
 */

//import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

//选择排序

public class SelectSort {

    public static void main(String[] args) {
	//int[] arr= {101, 34, 119, 1, -1, 90, 123};
	
	//测试一下选择排序的速度O(n^2),给80000个数据，测试
	//创建要给80000个的随机的数组
	int[]arr=new int[80000];
	for (int i = 0; i < 80000; i++) {
	    arr[i]=(int)(Math.random()*8000000);//生成一个[0,8000000)数
	}

	//System.out.println("排序前");
	//System.out.println(Arrays.toString(arr));
	
	Date date1=new Date();
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date1Str=simpleDateFormat.format(date1);
	System.out.println("排序前的时间="+date1Str);
	
	selectSort(arr);
	
	//System.out.println("排序后");
	//System.out.println(Arrays.toString(arr));
	
	Date date2=new Date();
	String date2Str=simpleDateFormat.format(date2);
	System.out.println("排序后的时间="+date2Str);
    }

    //选择排序
    public static void selectSort(int[] arr) {
	
	//在推导过程中，我们发现了规律，因此，可以使用for来解决
	//选择排序的时间复杂度是O(n^2)
	for (int i = 0; i < arr.length-1; i++) {
	    int minIndex=i;
	    int min=arr[i];
	    for (int j = i+1; j < arr.length; j++) {
		if (min>arr[j]) {//说明假定的最小值，并不是最小
		    min=arr[j];//重置min
		    minIndex=j;//重置minIndex
		}
	    }
	    //将最小值，放在arr[i]，即交换
	    if (minIndex!=i) {
		arr[minIndex]=arr[i];
		arr[i]=min;
	    }
	
	    //System.out.println("第"+(i+1)+"轮后");
	    //System.out.println(Arrays.toString(arr));//1， 34， 119， 101
	}
	
	/*
	
	//使用逐步推到的方式来，讲解选择排序
	//第1轮
	//原始的数组： 101， 34， 119， 1
	//第一轮排序： 1， 34， 119， 101
	//算法 先简单 -> 再复杂，就是可以把一个复杂的算法拆分成简单的问题->逐步解决
	
	//第1轮
	int minIndex=0;
	int min=arr[0];
	for (int j = 0+1; j < arr.length; j++) {
	    if (min>arr[j]) {//说明假定的最小值，并不是最小
		min=arr[j];//重置min
		minIndex=j;//重置minIndex
	    }
	}
	//将最小值，放在arr[0]，即交换
	if (minIndex!=0) {
	    arr[minIndex]=arr[0];
	    arr[0]=min;
	}
	
	System.out.println("第一轮后");
	System.out.println(Arrays.toString(arr));//1， 34， 119， 101
	
	//第2轮
	minIndex=1;
	min=arr[1];
	for (int j = 1+1; j < arr.length; j++) {
	    if (min>arr[j]) {//说明假定的最小值，并不是最小
		min=arr[j];//重置min
		minIndex=j;//重置minIndex
	    }
	}
	//将最小值，放在arr[1]，即交换
	if (minIndex!=1) {
	    arr[minIndex]=arr[1];
	    arr[1]=min;
	}
	
	System.out.println("第二轮后");
	System.out.println(Arrays.toString(arr));//1， 34， 119， 101
	
	//第3轮
	minIndex=2;
	min=arr[2];
	for (int j = 2+1; j < arr.length; j++) {
	    if (min>arr[j]) {//说明假定的最小值，并不是最小
		min=arr[j];//重置min
		minIndex=j;//重置minIndex
	    }
	}
	//将最小值，放在arr[2]，即交换
	if (minIndex!=2) {
	    arr[minIndex]=arr[2];
	    arr[2]=min;
	}
	
	System.out.println("第三轮后");
	System.out.println(Arrays.toString(arr));//1， 34， 101， 119
	
	*/
    }
    
}
