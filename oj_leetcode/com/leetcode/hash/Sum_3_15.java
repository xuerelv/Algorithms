package com.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum_3_15 {

	public static void main(String[] args) {
		// int[] S = { -1, 0, 1, 2, -1, -4, -2 };
		int[] S = { 7, -1, 14, -12, -8, 7, 2, -15, 8, 8, -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 1, -14, 4, 3, 10, -5, 2, 1, 6,
				11, 2, -2, -5, -7, -6, 2, -15, 11, -6, 8, -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 10, 14, 9, -8, -6, 4, -6, 11, 12,
				-15, 7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 0, 5, 14, -1, 14, 3, 8, 10,
				-8, 8, -5, -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 14, -2, -5, -11, 1, 3, -6 };

		List<List<Integer>> re = new Solution_Sum_3_15().threeSum(S);
		List<List<Integer>> re_my = new Solution_Sum_3_15().threeSum_my_overtime(S);

		if (re.size() != re_my.size()) {
			System.out.println("different");
		} else {
			for (int i = 0; i < re.size(); i++) {
				List<Integer> in = re.get(i);
				List<Integer> in_my = re_my.get(i);

				System.out.println(in.toString() + "   " + in_my.toString());
			}
		}
		System.out.println("true true true true");

		// for (int i = 0; i < re.size(); i++) {
		// List<Integer> in = re.get(i);
		//
		// // System.out.println("*****************");
		// for (int j = 0; j < in.size(); j++) {
		// System.out.print(in.get(j) + "   ");
		// }
		// System.out.println("*****************");
		// }
		//

	}

}

class Solution_Sum_3_15 {

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null) {
			return result;
		}
		Arrays.sort(nums);
		int index = 0;
		while (index < nums.length - 2 && nums[index] <= 0) {
			if (index > 0 && nums[index] == nums[index - 1]) {
				index++;
				continue;
			}
			int front = index + 1;
			int tail = nums.length - 1;
			while (front < tail) {
				if (nums[front] + nums[tail] == nums[index] * (-1)) {
					// 判断是否重复
					if (!(result.size() != 0 && result.get(result.size() - 1).get(0) == nums[index]
							&& result.get(result.size() - 1).get(1) == nums[front] && result.get(result.size() - 1).get(2) == nums[tail])) {

						List<Integer> item = new ArrayList<Integer>();
						item.add(nums[index]);
						item.add(nums[front]);
						item.add(nums[tail]);
						result.add(item);
					}
					front++;
					tail--;
				} else if (nums[front] + nums[tail] < nums[index] * (-1)) {
					front++;
				} else {
					tail--;
				}
			}
			index++;
		}
		return result;
	}

	//我的答案也是正确的，为何超时
	public List<List<Integer>> threeSum_my_overtime(int[] nums) {
		List<List<Integer>> re = new ArrayList<List<Integer>>();

		Arrays.sort(nums);
		System.out.println("###" + Arrays.toString(nums));
		int len = nums.length;
		if (len < 3)
			return re;
		for (int i = 0; i < len - 2 && nums[i] <= 0; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			List<Integer> mapping = new ArrayList<Integer>();
			for (int j = i + 1; j < len; j++) {
				if (mapping.contains(-nums[j] - nums[i])) {
					List<Integer> in = new ArrayList<Integer>();
					in.add(nums[i]);
					in.add(-nums[j] - nums[i]);
					in.add(nums[j]);
					if (!re.contains(in)) {
						re.add(in);
					}

				} else {
					mapping.add(nums[j]);
				}
			}
		}

		return re;
	}
}