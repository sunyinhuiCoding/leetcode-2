package com.msh.solutions._117_Populating_Next_Right_Pointers_in_Each_Node_2;

/**
 * Created by monkeysayhi on 2018/2/8.
 */


class TreeLinkNode {
  int val;
  TreeLinkNode left, right, next;

  TreeLinkNode(int x) {
    val = x;
  }
}

public class Solution {
  // 注意：不相邻的堂兄弟的也要相连
  // 分治不行
  // 常量空间，所以也不能额外申请变量记录状态；节点定义的也没办法用来记录状态
  // 因此，比较有可能在遍历的过程中处理
  // solution 1: “根右左”的先序dfs，保证左连右的时候，右面的叔伯节点已经串起来
  public void connect(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      return;
    }

    TreeLinkNode curChild = null;
    if (root.right != null) {
      curChild = root.right;
    }
    if (root.left != null) {
      if (root.right != null) {
        root.left.next = root.right;
      } else {
        curChild = root.left;
      }
    }
    assert curChild != null;

    TreeLinkNode nextRoot = null;
    nextRoot = root.next;
    while (nextRoot != null && nextRoot.left == null && nextRoot.right == null) {
      nextRoot = nextRoot.next;
    }
    if (nextRoot != null) {
      TreeLinkNode nextChild = nextRoot.left != null ? nextRoot.left : nextRoot.right;
      curChild.next = nextChild;
    }

    connect(root.right);
    connect(root.left);
  }
}