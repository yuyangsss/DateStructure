package LinkedList;

import java.util.Scanner;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");




        //创建一个链表

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        //加入
//           singleLinkedList.add(hero1);
//            singleLinkedList.add(hero2);
//          singleLinkedList.add(hero3);
//          singleLinkedList.add(hero4);
//          singleLinkedList.ShowList();

        singleLinkedList.AddByorder(hero2);
        singleLinkedList.AddByorder(hero1);
        singleLinkedList.AddByorder(hero4);
        singleLinkedList.AddByorder(hero3);

        singleLinkedList.ShowList();//显示一把

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "小玉麒麟");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后");
        singleLinkedList.ShowList();


        //删除节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后");
        singleLinkedList.ShowList();

        //求单链表的有效节点的个数
        System.out.println(getLength(singleLinkedList.getHead()));

        //测试倒数得到倒数第k个节点
        HeroNode res=FindLastIndexNode(singleLinkedList.getHead(),1);
        System.out.println(res);

        //反转单链表

        System.out.println("反转单链表");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.ShowList();

        //逆序打印单链表
        System.out.println("逆序打印单链表");
        reversePrin(singleLinkedList.getHead());
    }

    //求单链表的有效节点的个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur=head.next;//这里我们没有统计头结点
        while (cur!=null){
            length++;
            cur=cur.next;//遍历
        }
        return length;
    }

    //将单链表反转
    public static void reversetList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next==null||head.next.next==null){
            return;
        }
        //定义一个辅助指针（变量），帮助我们比那里原来的链表
        HeroNode cur=head.next;
        HeroNode next=null;//指向当前节点[cur]的下个节点
        HeroNode reverseHead=new HeroNode(0,"","");
        //遍历原来的链表
        //没遍历一个节点，就将其取出，并放在心得链表reverseHead 的最前端
        while(cur!=null){
            next=cur.next;//先保存当前节点的下一个节点，因为我们后面需要使用
            cur.next=reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next=cur;
            cur=next;//后移
        }
        head.next=reverseHead.next;
    }

    //方式2:逆序打印单链表
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrin(HeroNode head){
        if(head.next==null){
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入到栈中
        Stack<HeroNode> stack=new Stack<>();
        HeroNode cur=head.next;
        //将链表的所有节点都压入栈中
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;//cur后移，这样就可以压入下一个节点

        }
        //将栈中的节点打印，pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //查找单链表中的倒数第k个节点【新浪面试题】
    //思路
    //1.编写一个方法，接受head节点，同时接收一个index
    //2.index表示倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的总长度getlength
    //4.得到size后，我们从链表的第一个开始遍历(size-index)个,就可以得到
    //5.如果找到了，则返回该节点，否则返回null
    public static HeroNode FindLastIndexNode(HeroNode head,int index){
        //判断如果链表为空，返回Null
        if(head.next==null){
            return null;//没有找到
        }
        //第一个遍历得到链表的长度（节点个数）
        int size=getLength(head);
        //第二次遍历size-index位置，就是我们倒数的k的节点
        //先做一个index校验

        //定义辅助变量，for 循环定位倒数的index
        HeroNode cur=head.next;
        for(int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return  cur;
    }

}



class SingleLinkedList {
    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return head;
    }
    //添加节点到单项链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next，指向新的节点

    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果灭有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
         temp.next=heroNode ;
    }

    public void AddByorder(HeroNode heroNode) {
        //因为头结点不能动，因此我们仍然通过一个辅助指针（变量）来帮我们找到添加的位置
        //因为单链表，因为我们找到的temp，是位于添加位置的前一个节点，否则我们插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的标号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在  temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag) {//不能为true
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no不能修改，
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode newheroNode) {
        //判读是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完列表
            }
            if (temp.no == newheroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newheroNode.name;
            temp.nickname = newheroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找标号%d的节点，不能修改\n", newheroNode.no);

        }
    }

    //删除节点
    //1.head不能动，我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时发，是temp.next.no和需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除的节点的
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移
        }
        if (flag) {
            //找到可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }


    public void ShowList() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移，一定小心
            temp = temp.next;
        }
    }
}
//定义HeroNode,每个Heronode，对象就是一个节点
class  HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方便，我们重写toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\''
                +'}';
    }
}