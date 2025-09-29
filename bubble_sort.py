#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
冒泡排序算法实现
Bubble Sort Algorithm Implementation

冒泡排序是一种简单的排序算法，它重复地走访过要排序的数列，
一次比较两个元素，如果它们的顺序错误就把它们交换过来。
走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
"""


def bubble_sort(arr):
    """
    冒泡排序算法
    
    算法原理：
    1. 比较相邻的元素。如果第一个比第二个大，就交换它们两个
    2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对
    3. 在这一点，最大的元素应该在数组的最后位置
    4. 重复以上的步骤，每次比较的元素减少一个，直到没有任何一对数字需要比较
    
    时间复杂度：O(n²)
    空间复杂度：O(1)
    
    参数:
        arr (list): 需要排序的数组
    
    返回:
        list: 排序后的数组
    """
    if not arr:
        return arr
    
    n = len(arr)
    # 创建数组的副本，避免修改原数组
    sorted_arr = arr.copy()
    
    print(f"开始排序数组: {sorted_arr}")
    
    # 外层循环控制排序轮数
    for i in range(n):
        # 标记这一轮是否发生了交换
        swapped = False
        
        print(f"\n第 {i + 1} 轮排序:")
        
        # 内层循环进行相邻元素比较和交换
        # 由于每轮排序后最大元素都会"冒泡"到末尾，所以比较范围逐渐减小
        for j in range(0, n - i - 1):
            print(f"  比较 {sorted_arr[j]} 和 {sorted_arr[j + 1]}", end="")
            
            # 如果前一个元素大于后一个元素，则交换它们
            if sorted_arr[j] > sorted_arr[j + 1]:
                sorted_arr[j], sorted_arr[j + 1] = sorted_arr[j + 1], sorted_arr[j]
                swapped = True
                print(f" -> 交换后: {sorted_arr}")
            else:
                print(" -> 不需要交换")
        
        # 如果这一轮没有发生交换，说明数组已经有序，可以提前退出
        if not swapped:
            print(f"第 {i + 1} 轮没有发生交换，数组已经有序，提前退出")
            break
        
        print(f"第 {i + 1} 轮结束，当前数组: {sorted_arr}")
    
    print(f"\n排序完成: {sorted_arr}")
    return sorted_arr


def bubble_sort_optimized(arr):
    """
    优化版冒泡排序
    
    优化点：
    1. 如果某一轮没有发生交换，说明数组已经有序，可以提前退出
    2. 记录最后一次交换的位置，下次排序只需要到这个位置即可
    
    参数:
        arr (list): 需要排序的数组
    
    返回:
        list: 排序后的数组
    """
    if not arr:
        return arr
    
    n = len(arr)
    sorted_arr = arr.copy()
    
    print(f"开始优化冒泡排序: {sorted_arr}")
    
    # 记录最后一次交换的位置
    last_swap_pos = n - 1
    
    round_num = 0
    while last_swap_pos > 0:
        round_num += 1
        print(f"\n第 {round_num} 轮排序 (只需要比较到位置 {last_swap_pos}):")
        
        new_last_swap_pos = 0
        
        for j in range(last_swap_pos):
            print(f"  比较 {sorted_arr[j]} 和 {sorted_arr[j + 1]}", end="")
            
            if sorted_arr[j] > sorted_arr[j + 1]:
                sorted_arr[j], sorted_arr[j + 1] = sorted_arr[j + 1], sorted_arr[j]
                new_last_swap_pos = j
                print(f" -> 交换后: {sorted_arr}")
            else:
                print(" -> 不需要交换")
        
        last_swap_pos = new_last_swap_pos
        print(f"第 {round_num} 轮结束，当前数组: {sorted_arr}")
    
    print(f"\n优化排序完成: {sorted_arr}")
    return sorted_arr


def test_bubble_sort():
    """
    测试冒泡排序算法
    """
    print("=" * 60)
    print("冒泡排序算法测试")
    print("=" * 60)
    
    # 测试用例
    test_cases = [
        [64, 34, 25, 12, 22, 11, 90],  # 一般情况
        [5, 2, 4, 6, 1, 3],           # 小数组
        [1],                          # 单个元素
        [],                           # 空数组
        [1, 2, 3, 4, 5],              # 已经排序的数组
        [5, 4, 3, 2, 1],              # 逆序数组
        [3, 3, 3, 3],                 # 重复元素
        [7, 2, 9, 1, 5, 6]            # 随机顺序
    ]
    
    for i, test_case in enumerate(test_cases, 1):
        print(f"\n测试用例 {i}: {test_case}")
        print("-" * 40)
        
        # 测试基本冒泡排序
        print("基本冒泡排序:")
        result1 = bubble_sort(test_case)
        
        print("\n" + "=" * 40)
        
        # 测试优化冒泡排序
        print("优化冒泡排序:")
        result2 = bubble_sort_optimized(test_case)
        
        # 验证结果是否正确
        expected = sorted(test_case)
        assert result1 == expected, f"基本冒泡排序结果错误: {result1} != {expected}"
        assert result2 == expected, f"优化冒泡排序结果错误: {result2} != {expected}"
        
        print(f"\n✓ 测试用例 {i} 通过")
        print("=" * 60)


if __name__ == "__main__":
    # 运行测试
    test_bubble_sort()
    
    print("\n" + "=" * 60)
    print("冒泡排序算法演示完成！")
    print("=" * 60)
    
    # 交互式演示
    print("\n你可以输入自己的数组来测试冒泡排序:")
    try:
        user_input = input("请输入数字（用空格分隔，例如: 5 2 8 1 9）: ")
        if user_input.strip():
            numbers = list(map(int, user_input.split()))
            print(f"\n你输入的数组: {numbers}")
            result = bubble_sort(numbers)
            print(f"排序结果: {result}")
    except (ValueError, KeyboardInterrupt):
        print("输入无效或用户取消，演示结束。")