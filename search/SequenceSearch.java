package search;

public class SequenceSearch {

    public static void main(String[] args) {
	int[] arr= {1, 9, 11, -1, 34, 89};//û��˳�������
	int index=sequenceSearch(arr, -11);
	if (index==-1) {
	    System.out.println("û�в��ҵ�");
	} else {
	    System.out.println("�ҵ����±�="+index);
	}
    }
    
    /**
     * ��������ʵ�ֵ����Բ������ҵ�һ������������ֵ���ͷ���
     * @param arr
     * @param value
     * @return
     */
    public static int sequenceSearch(int[] arr, int value) {
	//���Բ�������һ�ȶԣ���������ͬ��ֵ���ͷ����±�
	for (int i = 0; i < arr.length; i++) {
	    if (arr[i]==value) {
		return i;
	    }
	}
	return -1;
    }

}
