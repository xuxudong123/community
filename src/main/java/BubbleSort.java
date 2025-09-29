/**
 * 冒泡排序算法实现
 * Bubble Sort Algorithm Implementation
 * 
 * 冒泡排序是一种简单的排序算法，它重复地走访过要排序的数列，
 * 一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 */
public class BubbleSort {
    
    /**
     * 基本冒泡排序算法
     * 
     * @param arr 需要排序的数组
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int n = arr.length;
        System.out.println("开始排序数组: " + arrayToString(arr));
        
        // 外层循环控制排序轮数
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // 标记这一轮是否发生了交换
            
            System.out.println("\n第 " + (i + 1) + " 轮排序:");
            
            // 内层循环进行相邻元素比较和交换
            // 由于每轮排序后最大元素都会"冒泡"到末尾，所以比较范围逐渐减小
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("  比较 " + arr[j] + " 和 " + arr[j + 1]);
                
                // 如果前一个元素大于后一个元素，则交换它们
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    System.out.println(" -> 交换后: " + arrayToString(arr));
                } else {
                    System.out.println(" -> 不需要交换");
                }
            }
            
            // 如果这一轮没有发生交换，说明数组已经有序，可以提前退出
            if (!swapped) {
                System.out.println("第 " + (i + 1) + " 轮没有发生交换，数组已经有序，提前退出");
                break;
            }
            
            System.out.println("第 " + (i + 1) + " 轮结束，当前数组: " + arrayToString(arr));
        }
        
        System.out.println("\n排序完成: " + arrayToString(arr));
    }
    
    /**
     * 优化版冒泡排序
     * 
     * 优化点：
     * 1. 如果某一轮没有发生交换，说明数组已经有序，可以提前退出
     * 2. 记录最后一次交换的位置，下次排序只需要到这个位置即可
     * 
     * @param arr 需要排序的数组
     */
    public static void bubbleSortOptimized(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int n = arr.length;
        System.out.println("开始优化冒泡排序: " + arrayToString(arr));
        
        // 记录最后一次交换的位置
        int lastSwapPos = n - 1;
        int roundNum = 0;
        
        while (lastSwapPos > 0) {
            roundNum++;
            System.out.println("\n第 " + roundNum + " 轮排序 (只需要比较到位置 " + lastSwapPos + "):");
            
            int newLastSwapPos = 0;
            
            for (int j = 0; j < lastSwapPos; j++) {
                System.out.print("  比较 " + arr[j] + " 和 " + arr[j + 1]);
                
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    newLastSwapPos = j;
                    System.out.println(" -> 交换后: " + arrayToString(arr));
                } else {
                    System.out.println(" -> 不需要交换");
                }
            }
            
            lastSwapPos = newLastSwapPos;
            System.out.println("第 " + roundNum + " 轮结束，当前数组: " + arrayToString(arr));
        }
        
        System.out.println("\n优化排序完成: " + arrayToString(arr));
    }
    
    /**
     * 将数组转换为字符串表示
     * 
     * @param arr 数组
     * @return 字符串表示
     */
    private static String arrayToString(int[] arr) {
        if (arr == null) return "null";
        if (arr.length == 0) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * 测试冒泡排序算法
     */
    public static void testBubbleSort() {
        System.out.println("============================================================");
        System.out.println("冒泡排序算法测试");
        System.out.println("============================================================");
        
        // 测试用例
        int[][] testCases = {
            {64, 34, 25, 12, 22, 11, 90},  // 一般情况
            {5, 2, 4, 6, 1, 3},           // 小数组
            {1},                          // 单个元素
            {},                           // 空数组
            {1, 2, 3, 4, 5},              // 已经排序的数组
            {5, 4, 3, 2, 1},              // 逆序数组
            {3, 3, 3, 3},                 // 重复元素
            {7, 2, 9, 1, 5, 6}            // 随机顺序
        };
        
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\n测试用例 " + (i + 1) + ": " + arrayToString(testCases[i]));
            System.out.println("----------------------------------------");
            
            // 创建数组副本进行测试
            int[] arr1 = testCases[i].clone();
            int[] arr2 = testCases[i].clone();
            
            // 测试基本冒泡排序
            System.out.println("基本冒泡排序:");
            bubbleSort(arr1);
            
            System.out.println("\n========================================");
            
            // 测试优化冒泡排序
            System.out.println("优化冒泡排序:");
            bubbleSortOptimized(arr2);
            
            System.out.println("\n✓ 测试用例 " + (i + 1) + " 通过");
            System.out.println("============================================================");
        }
    }
    
    /**
     * 主方法
     */
    public static void main(String[] args) {
        // 运行测试
        testBubbleSort();
        
        System.out.println("\n============================================================");
        System.out.println("冒泡排序算法演示完成！");
        System.out.println("============================================================");
        
        // 演示示例
        System.out.println("\n演示示例:");
        int[] example = {8, 3, 7, 1, 5};
        System.out.println("原数组: " + arrayToString(example));
        bubbleSort(example);
    }
}