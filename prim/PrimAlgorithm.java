package prim;

import java.util.Arrays;

/*
 * ��С������
 * ��·���Ȿ�ʾ�����С������������С������--MST
 * 1.����һ����Ȩ��������ͨͼ�����ѡ��һ����������ʹ�������б���Ȩ���ܺ�Ϊ��С�������С������
 * 2.N������һ����N-1����
 * 3.����ȫ������
 * 4.N-1���߶���ͼ��
 * 5.����С���������㷨��Ҫ������ķ�㷨�Ϳ�³˹�����㷨
 * 
 * ����ķ�㷨
 * 1.����ķ�㷨����С��������Ҳ�����ڰ���n���������ͨͼ�У��ҳ�ֻ�У�n-1�����߰�������n���������ͨ��ͼ��Ҳ������ν�ļ�С��ͨ��ͼ
 * 2.����ķ�㷨
 *  2.1��G=(V,E)����ͨͼ��T=(U,D)����С��������V,U�Ƕ��㼯�ϣ�E,D�Ǳߵļ���
 *  2.2���Ӷ���u��ʼ������С����������Ӽ���V��ȡ������u���뼯��U�У���Ƕ���vdvisited[u]=1
 *  2.3������U�ж���ui�뼯��V-U�ж���vj֮����ڱߣ���Ѱ����Щ����Ȩֵ��С�ıߣ������ܹ��ɻ�·��������vj���뼯��U�У�����(ui,vj)���뼯��D�����vdvisited[vj]=1
 *  2.4�ظ�����2��ֱ��U��V��ȣ������ж��㶼�����Ϊ���ʹ�����ʱD����n-1����
 */

public class PrimAlgorithm {

    public static void main(String[] args) {
	// ���Կ���ͼ�Ƿ񴴽�ok
	char[] data = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	int vertexs = data.length;

	// �ڽӾ���Ĺ�ϵʹ�ö�ά�����ʾ��10000�����������ʾ�����㲻��ͨ
	int[][] weight = new int[][] { { 10000, 5, 7, 10000, 10000, 10000, 2 }, { 5, 10000, 10000, 9, 10000, 10000, 3 },
		{ 7, 10000, 10000, 10000, 8, 10000, 10000 }, { 10000, 9, 10000, 10000, 10000, 4, 10000 },
		{ 10000, 10000, 8, 10000, 10000, 5, 4 }, { 10000, 10000, 10000, 4, 5, 10000, 6 },
		{ 2, 3, 10000, 10000, 4, 6, 10000 } };

	// MGraph����
	MGraph graph = new MGraph(vertexs);

	// ����һ��MinTree����
	MinTree minTree = new MinTree();

	minTree.createGraph(graph, vertexs, data, weight);

	// ���
	minTree.showGraph(graph);

	// ����prim�㷨
	minTree.prim(graph, 1);
    }

}

//������С������->��ׯ��ͼ
class MinTree {
    // ����ͼ���ڽӾ���
    /**
     * 
     * @param graph   ͼ����
     * @param vertexs ͼ��Ӧ�Ķ������
     * @param data    ͼ�ĸ��������ֵ
     * @param weight  ͼ���ڽӾ���
     */
    public void createGraph(MGraph graph, int vertexs, char[] data, int[][] weight) {
	int i, j;
	for (i = 0; i < vertexs; i++) {// ����
	    graph.data[i] = data[i];

	    for (j = 0; j < vertexs; j++) {
		graph.weight[i][j] = weight[i][j];
	    }
	}
    }

    // ��ʾͼ�ķ���
    public void showGraph(MGraph graph) {
	for (int[] link : graph.weight) {
	    System.out.println(Arrays.toString(link));
	}
    }

    // ��дprim�㷨���õ���С������
    /**
     * 
     * @param graph ͼ
     * @param v     ��ʾ��ͼ�ĵڼ������㿪ʼ���� 'A'->0, 'B'->1������
     */
    public void prim(MGraph graph, int v) {
	// visited[] ��ǽ�㣨���㣩�Ƿ񱻷��ʹ�
	int[] visited = new int[graph.vertexs];

	// visited[] Ĭ��Ԫ�ص�ֵ����0����ʾû�з��ʹ�
//	for (int i = 0; i < graph.vertexs; i++) {
//	    visited[1]=0;
//	}

	// �ѵ�ǰ��������Ϊ�ѷ���
	visited[v] = 1;

	// h1��h2��¼����������±�
	int h1 = -1;
	int h2 = -1;
	int minWeight = 10000;// ��minWeight��ʼ��һ�������������ڱ��������У��ᱻ�滻

	for (int k = 1; k < graph.vertexs; k++) {// ��Ϊ��graph.vertexs���㣬prim�㷨��������graph.vertexs-1��
	    // �����ȷ��ÿһ�����ɵ���ͼ�����ĸ����ľ������
	    for (int i = 0; i < graph.vertexs; i++) {// i����ʾ�����ʹ��Ľ��
		for (int j = 0; j < graph.vertexs; j++) {// j����ʾ��û�з��ʹ��Ľ��
		    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
			// �滻minWeight��Ѱ���Ѿ����ʹ��Ľ���δ���ʹ��Ľ���Ȩֵ��С�ıߣ�
			minWeight = graph.weight[i][j];
			h1 = i;
			h2 = j;
		    }
		}
	    }
	    // �ҵ�һ��������С
	    System.out.println("�� <" + graph.data[h1] + "��" + graph.data[h2] + "> Ȩֵ��" + minWeight);

	    // ����ǰ��ǰ��������Ϊ�Ѿ�����
	    visited[h2] = 1;

	    // minWeight��������Ϊ���ֵ10000
	    minWeight = 10000;
	}
    }
}

class MGraph {
    int vertexs;// ��ʾͼ������
    char[] data;// ��Ž������
    int[][] weight;// ��űߣ��������ǵ��ڽӾ���

    public MGraph(int vertexs) {
	// TODO �Զ����ɵĹ��캯�����
	this.vertexs = vertexs;
	data = new char[vertexs];
	weight = new int[vertexs][vertexs];
    }
}