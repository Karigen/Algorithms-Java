package divideandconquer;

/*
 * 分治算法--分而治之-递归
 * 1.分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的的子问题
 * 2.解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
 * 3.合并：将各个子问题地解合并为原问题的解
 * 
 * 递归的精髓：抽象地想，尽量不要具象地思考问题
 * 
 * 汉诺塔
 * 1.如果只有一个盘，A->C
 * 2.如果n>=2情况，我们总是可以看作是两个盘 1.最下面一个的盘 2.上面的所有盘
 *  2.1先把上面的所有盘 A->B，移动过程会使用到c
 *  2.2把最下边的盘A->C
 *  2.3把B塔的所有盘从B->C，移动过程使用到a塔
 */

public class HanoiTower {

    public static void main(String[] args) {
	hanoiTower(5, 'A', 'B', 'C');
    }

    // 汉诺塔的移动方法
    // 使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
	// 如果只有一个盘
	if (num == 1) {
	    System.out.println("第1个盘从 " + a + "->" + c);
	} else {
	    // 如果n>=2情况，我们总是可以看作是两个盘 1.最下面一个的盘 2.上面的所有盘
	    // 1.先把上面的所有盘 A->B，移动过程会使用到c
	    hanoiTower(num - 1, a, c, b);

	    // 2.把最下边的盘A->C
	    System.out.println("第" + num + "个盘从 " + a + "->" + c);

	    // 3.把B塔的所有盘从B->C，移动过程使用到a塔
	    hanoiTower(num - 1, b, a, c);
	}
    }

}