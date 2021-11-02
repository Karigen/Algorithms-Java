package prim;

import java.util.Arrays;

/*
 * 最小生成树
 * 修路问题本质就是最小生成树问题最小生成树--MST
 * 1.给定一个带权的无向连通图，如何选择一棵生成树，使树上所有边上权的总和为最小，这叫最小生成树
 * 2.N个顶点一定有N-1条边
 * 3.包含全部顶点
 * 4.N-1条边都在图中
 * 5.求最小生成树的算法主要是普利姆算法和克鲁斯卡尔算法
 * 
 * 普利姆算法
 * 1.普利姆算法求最小生成树，也就是在包含n个顶点的连通图中，找出只有（n-1）条边包含所有n个顶点的连通子图，也就是所谓的极小连通子图
 * 2.普利姆算法
 *  2.1设G=(V,E)是连通图，T=(U,D)是最小生成树，V,U是顶点集合，E,D是边的集合
 *  2.2若从顶点u开始构造自小生成树，则从集合V中取出顶点u放入集合U中，标记顶点vdvisited[u]=1
 *  2.3若集合U中顶点ui与集合V-U中顶点vj之间存在边，则寻找这些边中权值最小的边，但不能构成回路，将顶点vj加入集合U中，将边(ui,vj)加入集合D，标记vdvisited[vj]=1
 *  2.4重复步骤2，直到U与V相等，及所有顶点都被标记为访问过，此时D中有n-1条边
 */

public class PrimAlgorithm {

    public static void main(String[] args) {
	// 测试看看图是否创建ok
	char[] data = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	int vertexs = data.length;

	// 邻接矩阵的关系使用二维数组表示，10000这个大数，表示两个点不连通
	int[][] weight = new int[][] { { 10000, 5, 7, 10000, 10000, 10000, 2 }, { 5, 10000, 10000, 9, 10000, 10000, 3 },
		{ 7, 10000, 10000, 10000, 8, 10000, 10000 }, { 10000, 9, 10000, 10000, 10000, 4, 10000 },
		{ 10000, 10000, 8, 10000, 10000, 5, 4 }, { 10000, 10000, 10000, 4, 5, 10000, 6 },
		{ 2, 3, 10000, 10000, 4, 6, 10000 } };

	// MGraph对象
	MGraph graph = new MGraph(vertexs);

	// 创建一个MinTree对象
	MinTree minTree = new MinTree();

	minTree.createGraph(graph, vertexs, data, weight);

	// 输出
	minTree.showGraph(graph);

	// 测试prim算法
	minTree.prim(graph, 1);
    }

}

//创建最小生成树->村庄的图
class MinTree {
    // 创建图的邻接矩阵
    /**
     * 
     * @param graph   图对象
     * @param vertexs 图对应的顶点个数
     * @param data    图的各个顶点的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertexs, char[] data, int[][] weight) {
	int i, j;
	for (i = 0; i < vertexs; i++) {// 顶点
	    graph.data[i] = data[i];

	    for (j = 0; j < vertexs; j++) {
		graph.weight[i][j] = weight[i][j];
	    }
	}
    }

    // 显示图的方法
    public void showGraph(MGraph graph) {
	for (int[] link : graph.weight) {
	    System.out.println(Arrays.toString(link));
	}
    }

    // 编写prim算法，得到最小生成树
    /**
     * 
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成 'A'->0, 'B'->1···
     */
    public void prim(MGraph graph, int v) {
	// visited[] 标记结点（顶点）是否被访问过
	int[] visited = new int[graph.vertexs];

	// visited[] 默认元素的值都是0，表示没有访问过
//	for (int i = 0; i < graph.vertexs; i++) {
//	    visited[1]=0;
//	}

	// 把当前这个结点标记为已访问
	visited[v] = 1;

	// h1和h2记录两个顶点的下标
	int h1 = -1;
	int h2 = -1;
	int minWeight = 10000;// 将minWeight初始成一个大数，后面在遍历过程中，会被替换

	for (int k = 1; k < graph.vertexs; k++) {// 因为有graph.vertexs顶点，prim算法结束后，有graph.vertexs-1边
	    // 这个是确定每一次生成的子图，和哪个结点的距离最近
	    for (int i = 0; i < graph.vertexs; i++) {// i结点表示被访问过的结点
		for (int j = 0; j < graph.vertexs; j++) {// j结点表示还没有访问过的结点
		    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
			// 替换minWeight（寻找已经访问过的结点和未访问过的结点间权值最小的边）
			minWeight = graph.weight[i][j];
			h1 = i;
			h2 = j;
		    }
		}
	    }
	    // 找到一条边是最小
	    System.out.println("边 <" + graph.data[h1] + "，" + graph.data[h2] + "> 权值：" + minWeight);

	    // 将当前当前这个结点标记为已经访问
	    visited[h2] = 1;

	    // minWeight重新设置为最大值10000
	    minWeight = 10000;
	}
    }
}

class MGraph {
    int vertexs;// 表示图结点个数
    char[] data;// 存放结点数据
    int[][] weight;// 存放边，就是我们的邻接矩阵

    public MGraph(int vertexs) {
	// TODO 自动生成的构造函数存根
	this.vertexs = vertexs;
	data = new char[vertexs];
	weight = new int[vertexs][vertexs];
    }
}