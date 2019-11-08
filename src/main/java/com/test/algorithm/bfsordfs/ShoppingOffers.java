package com.test.algorithm.bfsordfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 638. 大礼包
 * 在LeetCode商店中， 有许多在售的物品。
 * <p>
 * 然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * <p>
 * 现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
 * <p>
 * 每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。
 * <p>
 * 任意大礼包可无限次购买。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * 输出: 14
 * 解释:
 * 有A和B两种物品，价格分别为¥2和¥5。
 * 大礼包1，你可以以¥5的价格购买3A和0B。
 * 大礼包2， 你可以以¥10的价格购买1A和2B。
 * 你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
 * 示例 2:
 * <p>
 * 输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * 输出: 11
 * 解释:
 * A，B，C的价格分别为¥2，¥3，¥4.
 * 你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。
 * 你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
 * 你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
 * 说明:
 * <p>
 * 最多6种物品， 100种大礼包。
 * 每种物品，你最多只需要购买6个。
 * 你不可以购买超出待购清单的物品，即使更便宜。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shopping-offers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/11/8
 */
public class ShoppingOffers {
    static class Solution {
        private List<Integer> global_needs;
        private List<List<Integer>> global_special;
        private List<Integer> global_price;
        private int sum = 0;
        private int res = Integer.MAX_VALUE;

        public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
            //给全局变量赋值(引用传递)
            global_needs = needs;
            global_special = special;
            global_price = price;
            find(0);
            return res;
        }

        private void find(int begin) {
            // 没有显式的递归终止

            //备份sum值
            int temp_sum = sum;
            // 把global_needs中的物品依次用 单价购买 的形式 补齐
            for (int i = 0; i < global_needs.size(); ++i)
                sum += global_needs.get(i) * global_price.get(i);
            //res取最小值
            res = Math.min(res, sum);
            //从备份中恢复sum值
            sum = temp_sum;
            //以入参begin为起点，遍历global_special
            for (int i = begin; i < global_special.size(); ++i) {
                //给当前大礼包 取的变量名，不然看的费劲
                List<Integer> cur_special = global_special.get(i);
                //global_needs中的物品 最多需要几个当前的大礼包
                int special_num = cal_special_num(cur_special);
                //global_needs中的物品 并不需要当前的大礼包(0个)，跳过
                if (special_num == 0)
                    continue;
                //根据 当前大礼包所需的数量，for循环 + 递归调用
                for (int j = 1; j <= special_num; ++j) {
                    //备份当前global_needs
                    List<Integer> temp_needs = new ArrayList<>(global_needs);
                    //修改global_needs的所需物品个数
                    for (int k = 0; k < global_needs.size(); ++k)
                        global_needs.set(k, global_needs.get(k) - cur_special.get(k) * j);
                    //算账(+ 大礼包单价 × 大礼包数量)
                    sum += cur_special.get(global_needs.size()) * j;
                    //递归调用
                    find(i + 1);
                    //从备份中恢复global_needs
                    global_needs = temp_needs;
                    //从备份中恢复sum
                    sum = temp_sum;
                }
            }
        }

        //入参item：指大礼包
        //计算global_needs中的物品，最多需要几个 当前大礼包
        private int cal_special_num(List<Integer> item) {
            //max初始化为 Integer的最大值
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < global_needs.size(); ++i) {
                //global_needs中 当前下标的所需物品个数 < 大礼包中当前下标的物品个数，直接return 0
                //不能超出待购清单的物品个数
                if (global_needs.get(i) < item.get(i))
                    return 0;
                //防止除数为0，抛出异常(跳过 大礼包中当前下标的物品)
                if (item.get(i) == 0)
                    continue;
                //木桶效应(取最小值)
                max = Math.min(global_needs.get(i) / item.get(i), max);
            }
            return max;
        }
    }

    static class Solution1 {
        public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
            //统计不使用大礼包的总价
            int noSpecial = 0;
            for (int i = 0; i < needs.size(); i++) {
                noSpecial += price.get(i) * needs.get(i);
            }
            int res = noSpecial;
            //遍历每一个大礼包
            for (List<Integer> sp : special) {
                //当前大礼包超过购买数量，跳过
                if (check(sp, needs)) {
                    //使用当前大礼包后，还有多少剩下的
                    List<Integer> newNeeds = new ArrayList<>();
                    for (Integer i = 0; i < sp.size() - 1; i++) {
                        newNeeds.add(needs.get(i) - sp.get(i));
                    }
                    //剩下的购买数量递归调用本方法，获取最低价格
                    int left = shoppingOffers(price, special, newNeeds);
                    //使用当前大礼包和不使用相比，选价格最低的
                    res = Math.min(res, left + sp.get(sp.size() - 1));
                }
            }
            return res;
        }

        private boolean check(List<Integer> special, List<Integer> needs) {
            for (int i = 0; i < needs.size(); i++) {
                if (special.get(i) > needs.get(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<>();
        List<List<Integer>> special = new ArrayList<>();
        List<Integer> needs = new ArrayList<>();
        price.add(2);
        price.add(5);
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(0);
        list1.add(5);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(10);
        special.add(list1);
        special.add(list2);
        needs.add(3);
        needs.add(2);
        System.out.println(new Solution1().shoppingOffers(price, special, needs));
    }
}
