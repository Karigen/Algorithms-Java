package floyd;

import java.util.Arrays;

/*
 * 弗洛伊德算法介绍
 * 1.弗洛伊德算法也是一种用与寻找给定的加权图中顶点间最短路径的算法
 * 2.弗洛伊德算法计算图中各个顶点之间的最短路径
 * 3.迪杰斯特拉算法用于计算图中某一个顶点到其他顶点的最短路径
 * 4.弗洛伊德算法VS迪杰斯特拉算法:
 * 	迪杰斯特拉算法通过选定的被访问顶点,求出从出发顶点到其他顶点的最短路径;
 * 	弗洛伊德算法中的每一个顶点都是出发顶点,所以需要将每一个顶点看作被访问顶点,求出从每一个顶点到其他顶点的最短路径.
 * 	相当于将图中每一个顶点设置为初始顶点开始迪杰斯特拉算法.
 * 
 * 弗洛伊德算法分析
 * 1.设置顶点vi到顶点vk的最短路径已知为Lik,顶点vk到vj的最短路径已知为Lkj,顶点vi到vj的路径为Lij,则vi到vj的最短路径为:min((Lik+Lkj), Lij),vk的取值为图中所有点点,则可获得vi到vj的最短路径
 * 2.至于vi到vk的最短路径Lik或者vk到vj的最短路径Lkj,是以同样的方式获得(个人感觉虽然不是递归但是有点递归的思想在里面,中间看似只有一个顶点k,实际上有很多顶点)
 */

public class FloydAlgorithm {

    public static void main(String[] args) {
	// TODO 自动生成的方法存根
	// 测试看看图是否创建成功
	char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	// 创建邻接矩阵
	int[][] matrix = new int[vertex.length][vertex.length];
	final int N = 65535;

	matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
	matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
	matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
	matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
	matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
	matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
	matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

	// 创建Graph对象
	Graph graph = new Graph(vertex.length, matrix, vertex);
	
	//调用弗洛伊德算法
	graph.floyd();
	
	graph.show();
    }

}

//创建图
class Graph {
    private char[] vertex;// 存放顶点的数组
    private int[][] dis;// 保存，从各个顶点出发到其他顶点的距离，最后的结果，也是保留在该数组
    private int[][] pre;// 保存到达目标顶点的前驱顶点

    // 构造器
    /**
     * 
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
	// TODO 自动生成的构造函数存根
	this.vertex = vertex;
	this.dis = matrix;
	this.pre = new int[length][length];

	// 对pre数组初始化，注意存放的是前驱顶点的下标
	for (int i = 0; i < vertex.length; i++) {
	    Arrays.fill(pre[i], i);
	}
    }

    // 显示pre数组和dis数组
    public void show() {
	// 为了显示便于阅读，我们优化一下输出
	char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
	for (int k = 0; k < dis.length; k++) {
	    // 先将pre数组输出的一行
	    for (int i = 0; i < dis.length; i++) {
		System.out.print(vertex[pre[k][i]] + " ");
	    }

	    System.out.println();

	    // 输出dis数组的一行数据
	    for (int i = 0; i < dis.length; i++) {
		System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径是" + dis[k][i] + "） ");
	    }

	    System.out.println();
	    System.out.println();
	}
    }
    
    //弗洛伊德算法
    public void floyd() {
	int len=0;//变量保存距离
	
	//对中间顶点的遍历，k就是中间顶点的下标[ 'A', 'B', 'C', 'D', 'E', 'F', 'G' ]
	for (int k = 0; k < dis.length; k++) {//
	    //从i顶点开始出发[ 'A', 'B', 'C', 'D', 'E', 'F', 'G' ]
	    for (int i = 0; i < dis.length; i++) {
		//到达j顶点//[ 'A', 'B', 'C', 'D', 'E', 'F', 'G' ]
		for (int j = 0; j < dis.length; j++) {
		    len=dis[i][k]+dis[k][j];//=>求出从i顶点出发，经过k中间顶点，到达j顶点
		    
		    if (len<dis[i][j]) {//如果len小于dis[i][j]
			dis[i][j]=len;//更新距离
			pre[i][j]=pre[k][j];//更新前驱顶点
		    }
		}
	    }
	}
    }
}