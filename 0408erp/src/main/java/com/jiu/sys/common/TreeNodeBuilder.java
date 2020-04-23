package com.jiu.sys.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TreeNodeBuilder
 * @Author Jiu
 * @Create 2020/4/9 11:39
 **/
public class TreeNodeBuilder {

    public static List<TreeNode> build(List<TreeNode> treeNodes,Integer topPid){
        List<TreeNode> nodes=new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            if(n1.getPid().equals(topPid)){
                nodes.add(n1);
            }
            for (TreeNode n2 : treeNodes) {
                if(n1.getId().equals(n2.getPid())){
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}
