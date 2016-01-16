package com.america_leval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ninth_leval {

	public static void main(String[] args) {
		// System.out.println(new Solution_Ninth_leval_2().climbStairs(4));
		int[] A = { 3, 6, 5, 8 };
		System.out.println(new Solution_Ninth_leval_9().backPack(10, A));
	}

}

// 最小调整代价
class Solution_Ninth_leval_12 {
	/**
	 * @param A:
	 *            An integer array.
	 * @param target:
	 *            An integer.
	 */
	public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
		
		
		
		
	}
}

// 最小子数组
class Solution_Ninth_leval_11 {
	/**
	 * @param nums:
	 *            a list of integers
	 * @return: A integer indicate the sum of minimum subarray
	 */
	public int minSubArray(ArrayList<Integer> nums) {
		if (nums.size() == 0)
			return 0;

		int min = nums.get(0);
		int this_min = nums.get(0);
		for (int i = 1; i < nums.size(); i++) {
			this_min = Math.min(this_min + nums.get(i), nums.get(i));
			min = Math.min(this_min, min);
		}

		return min;
	}
}

// 交叉字符串
class Solution_Ninth_leval_10 {
	/**
	 * Determine whether s3 is formed by interleaving of s1 and s2.
	 * 
	 * @param s1,
	 *            s2, s3: As description.
	 * @return: true or false.
	 */
	public boolean isInterleave_2(String s1, String s2, String s3) {
		if ((s1.length() + s2.length()) != s3.length())
			return false;

		boolean[][] matrix = new boolean[s2.length() + 1][s1.length() + 1];
		matrix[0][0] = true;
		for (int i = 1; i < matrix[0].length; i++) {
			matrix[0][i] = matrix[0][i - 1] && (s1.charAt(i - 1) == s3.charAt(i - 1));
		}
		for (int i = 1; i < matrix.length; i++) {
			matrix[i][0] = matrix[i - 1][0] && (s2.charAt(i - 1) == s3.charAt(i - 1));
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				matrix[i][j] = (matrix[i - 1][j] && (s2.charAt(i - 1) == s3.charAt(i + j - 1)))
						|| (matrix[i][j - 1] && (s1.charAt(j - 1) == s3.charAt(i + j - 1)));
			}
		}

		return matrix[s2.length()][s1.length()];
	}

	// 回溯法，可以通过
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length())
			return false;
		return search_down(s1, 0, s2, 0, s3, 0);
	}

	boolean search_down(String s1, Integer s1_start, String s2, Integer s2_start, String s3, int s3_staart) {
		if (s3_staart == s3.length())
			return true;

		if (s1_start < s1.length() && s1.charAt(s1_start) != s3.charAt(s3_staart) && s2_start < s2.length()
				&& s2.charAt(s2_start) != s3.charAt(s3_staart))
			return false;

		boolean re = false;
		if (s1_start < s1.length() && s1.charAt(s1_start) == s3.charAt(s3_staart)) {
			re = search_down(s1, s1_start + 1, s2, s2_start, s3, s3_staart + 1);
		}

		if (s2_start < s2.length() && s2.charAt(s2_start) == s3.charAt(s3_staart)) {
			re |= search_down(s1, s1_start, s2, s2_start + 1, s3, s3_staart + 1);
		}

		return re;
	}

}

// 背包问题
class Solution_Ninth_leval_9 {
	/**
	 * @param m:
	 *            An integer m denotes the size of a backpack
	 * @param A:
	 *            Given n items with size A[i]
	 * @return: The maximum size
	 */
	public int backPack(int m, int[] A) {
		int[][] dp = new int[A.length][m + 1];

		for (int j = 1; j < m + 1; j++) {
			if (A[0] <= j) {
				dp[0][j] = A[0];
			}
			for (int i = 1; i < A.length; i++) {
				if (A[i] > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j - A[i]] + A[i], dp[i - 1][j]);
				}
			}
		}
		return dp[A.length - 1][m];
	}
}

// 单词切分
class TrieNode {
	char ch;
	boolean has_word;
	HashMap<Character, TrieNode> children;

	public TrieNode() {
		this.ch = ' ';
		this.has_word = false;
		this.children = new HashMap<>();
	}

	public TrieNode(char c) {
		this.ch = c;
		this.has_word = false;
		this.children = new HashMap<>();
	}
}

class Trie {
	public TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode cur = root;
		Map<Character, TrieNode> cur_children = root.children;

		char[] word_arr = word.toCharArray();
		for (int i = 0; i < word_arr.length; i++) {
			char wc = word_arr[i];
			cur = cur_children.get(wc);
			if (cur == null) {
				TrieNode new_node = new TrieNode(wc);
				cur_children.put(wc, new_node);
				cur = new_node;
			}

			cur_children = cur.children;
		}
		cur.has_word = true;
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode cur = root;
		Map<Character, TrieNode> cur_children = root.children;

		char[] word_arr = word.toCharArray();
		for (int i = 0; i < word_arr.length; i++) {
			char wc = word_arr[i];
			cur = cur_children.get(wc);
			if (cur != null) {
				cur_children = cur.children;
			} else {
				return false;
			}
		}
		return cur.has_word;
	}
}

class Solution_Ninth_leval_8 {
	/**
	 * @param s:
	 *            A string s
	 * @param dict:
	 *            A dictionary of words dict
	 */
	public boolean wordBreak(String s, Set<String> dict) {
		int len = s.length();
		boolean[] f = new boolean[len + 1];
		f[0] = true;

		Trie word_dict = new Trie();
		for (String word : dict) {
			word_dict.insert(word);
		}

		Trie second_word_dict = new Trie();
		String temp;
		for (int i = 1; i < len + 1; i++) {
			for (int j = i - 1; j >= 0; j--) {
				temp = s.substring(j, i);
				if (f[j]) {
					if (second_word_dict.search(temp)) {
						f[i] = true;
						break;
					}
					if (word_dict.search(temp)) {
						second_word_dict.insert(temp);
						f[i] = true;
						break;
					}
				}
			}
		}
		return f[len];
	}
}

// 不同的子序列
class Solution_Ninth_leval_7 {
	/**
	 * @param S,
	 *            T: Two string.
	 * @return: Count the number of distinct subsequences
	 */
	public int numDistinct(String s, String t) {
		if (s == null || t == null)
			return 0;
		if (s.length() < t.length())
			return 0;
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		dp[0][0] = 1;
		// 将任意一个字符串变成空串，都为1
		for (int i = 0; i < s.length(); i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= t.length(); j++) {
				dp[i][j] = dp[i - 1][j];
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					dp[i][j] += dp[i - 1][j - 1];
				}
			}
		}

		return dp[s.length()][t.length()];
	}
}

// 编辑距离
class Solution_Ninth_leval_6 {
	/**
	 * @param word1
	 *            & word2: Two string.
	 * @return: The minimum number of steps.
	 */
	public int minDistance(String word1, String word2) {

		int len1 = word1.length() + 1;
		int len2 = word2.length() + 1;

		// 初始化纪录矩阵，这好像是动态规划的标配
		int[][] mark = new int[len1][len2];
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				mark[i][j] = Integer.MAX_VALUE;
			}
		}

		mark[0][0] = 0;
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				// 这里是有一个先后关系的，先尝试插入与删除，然后才是比较是否相等，因为这三种操作方法是并行的，没有优先级关系
				if (i > 0)
					mark[i][j] = Math.min(mark[i][j], mark[i - 1][j] + 1); // delete
				if (j > 0)
					mark[i][j] = Math.min(mark[i][j], mark[i][j - 1] + 1);// insert

				// substitute
				if (i > 0 && j > 0) {
					if (word1.charAt(i - 1) != word2.charAt(j - 1))
						mark[i][j] = Math.min(mark[i][j], mark[i - 1][j - 1] + 1);
					else
						mark[i][j] = Math.min(mark[i][j], mark[i - 1][j - 1]);
				}
			}
		}

		return mark[len1 - 1][len2 - 1];

	}
}

// 乘积最大子序列
class Solution_Ninth_leval_5 {
	/**
	 * @param nums:
	 *            an array of integers
	 * @return: an integer
	 */
	public int maxProduct(int[] nums) {
		if (nums.length == 0)
			return 0;
		if (nums.length == 1)
			return nums[0];

		int max_local = nums[0];
		int min_local = nums[0];
		int global = nums[0];

		for (int i = 1; i < nums.length; i++) {
			int max_copy = max_local;
			// 因为这里是乘积，所以要同时纪录最大值与最小值
			max_local = Math.max(Math.max(max_copy * nums[i], nums[i]), min_local * nums[i]);
			min_local = Math.min(Math.min(max_copy * nums[i], nums[i]), min_local * nums[i]);
			global = Math.max(global, max_local);
		}

		return global;

	}
}

// 最大子数组
class Solution_Ninth_leval_4 {
	/**
	 * @param nums:
	 *            A list of integers
	 * @return: A integer indicate the sum of max subarray
	 */
	public int maxSubArray(int[] nums) {
		if (nums.length == 0)
			return 0;

		int max = nums[0];
		int this_max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			this_max = Math.max(this_max + nums[i], nums[i]);
			max = Math.max(this_max, max);
		}

		return max;
	}
}

// 数字三角形
class Solution_Ninth_leval_3 {
	/**
	 * @param triangle:
	 *            a list of lists of integers.
	 * @return: An integer, minimum path sum.
	 */
	public int minimumTotal(int[][] triangle) {
		int[] A = new int[triangle.length + 1];
		for (int i = triangle.length - 1; i >= 0; i--) {
			for (int j = 0; j < triangle[i].length; j++) {
				A[j] = Math.min(A[j], A[j + 1]) + triangle[i][j];
			}
		}
		return A[0];
	}
}

// 爬楼梯
class Solution_Ninth_leval_2 {
	/**
	 * @param n:
	 *            An integer
	 * @return: An integer
	 */
	public int climbStairs(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		int a = 1;
		int b = 2;

		int tmp;
		while (n > 2) {
			tmp = b;
			b = a + b;
			a = tmp;
			n--;
		}

		return b;
	}
}

// 不同的路径 II
class Solution_Ninth_leval_1 {
	/**
	 * @param obstacleGrid:
	 *            A list of lists of integers
	 * @return: An integer
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0)
			return 0;

		int[][] path_num = new int[obstacleGrid.length][obstacleGrid[0].length];

		for (int i = 0; i < obstacleGrid.length; i++) {
			if (obstacleGrid[i][0] == 1)
				break;
			else
				path_num[i][0] = 1;
		}

		for (int j = 0; j < obstacleGrid[0].length; j++) {
			if (obstacleGrid[0][j] == 1)
				break;
			else
				path_num[0][j] = 1;
		}

		for (int i = 1; i < obstacleGrid.length; i++) {
			for (int j = 1; j < obstacleGrid[0].length; j++) {
				if (obstacleGrid[i][j] == 1) {
					path_num[i][j] = 0;
				} else {
					path_num[i][j] = path_num[i - 1][j] + path_num[i][j - 1];
				}
			}
		}

		return path_num[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
	}
}