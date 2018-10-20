#include <iostream>
#include <cstdarg>
#include <string.h>
#define order 4
#define true 1;
#define false 0 ;

using namespace std;

typedef struct BTNode {

    int keynum;
    int key[order + 1];
    struct BTNode *parent;
    struct BTNode *children[order + 1];

} BTNode, *BTree;

typedef struct {
    BTree tree;
    int pos;
    int tag;
} Result;


/**
 *
 * @param tree 树的节点
 * @param key  查询的key
 * @return 查询该key在该节点中key数组的位置
 */
int SearchKey(BTree tree, int key) {
    int i = 1;
    while (i <= tree->keynum && key > tree->key[i]) i++;
    return i;
}

/**
 *
 * @param tree 查询的树
 * @param key 查询的关键字
 * @return 返回查询到的结果结构体
 */
Result *Search(BTree tree, int key) {
    Result *result = (Result *) malloc(sizeof(Result));
    BTree cur = tree, pre = nullptr;
    int pos = 0;
    int found = false;

    while (cur != nullptr && !found) {
        //查询key在该节点中key数组的位置
        pos = SearchKey(cur, key);
        //找到该位置 将found标记设1,该位置表示该key所在的位置 , 否则该位置将表示插入的位置
        if (pos <= cur->keynum && cur->key[pos] == key) found = 1;
        else {
            pre = cur;
            //下移指针
            cur = cur->children[pos - 1];
        }
    }

    if (!found) {
        result->pos = pos;
        result->tree = pre;
        result->tag = 0;
    } else {
        result->tag = 1;
        result->tree = cur;
        result->pos = pos;
    }

    return result;

}

/**
 *
 * @param tree 新建的根节点
 * @param left 根节点的第一个孩子
 * @param right 根节点的第二个孩子
 * @param key 根节点的key
 * 以left树 right树 构建一个 以 key 为根 的树
 */
void newRoot(BTree &tree , BTree left , BTree right , int key){
    tree = (BTree)malloc(sizeof(BTNode));
    tree->keynum = 1;
    tree->key[1] = key;
    tree->children[0] = left;
    tree->children[1] = right;
    tree->parent = nullptr;
    if(left != nullptr) left->parent = tree;
    if(right != nullptr) right->parent = tree;

};

/**
 *
 * @param tree 即将分裂的树 , 并且是分出的左部分
 * @param partRight 分裂出的右部分
 * @param pos 以该位置开始分裂
 *
 *
 */
void splitTree(BTree &tree , BTree &partRight , int pos){

    partRight = (BTree)malloc(sizeof(BTNode));

    //复制右部分
    partRight->children[0] = tree->children[pos];
    for(int i = 1 , j = pos+1 ; j <= tree->keynum ; j++,i++){
        partRight->children[i] = tree->children[j];
        partRight->key[i] = tree->key[j];
    }

    //分裂的时候左部分仍保留与父亲节点的链接,而右部分给分裂出去,需要重新接入父亲节点中
    partRight->parent = tree->parent;
    partRight->keynum = tree->keynum - pos;
    //修改新节点的儿子的双亲节点
    for(int i = 0 ; i <= partRight->keynum ; i++){
        if( partRight->children[i]) {
            partRight->children[i]->parent = partRight;
        }
    }

    //假装删除了原树中的右部分
    tree->keynum = pos -1;
    //并删除
    for( int i = tree->keynum ; i < order ; i ++){

    }


}

/**
 *
 * @param tree 插入该节点为根的树,只插入到该根内
 * @param pos 插入的位置
 * @param key 插入的key
 * @param childPtr 插入后新增加的儿子子指针
 */
void InsertAt(BTree &tree , int pos , int key,BTree childPtr ){

    //POS与POS后数组后移

    for(int i = tree->keynum ; i >= pos ; i--){
        tree->key[i+1] = tree->key[i];
        tree->children[i+1] = tree->children[i];
    }

    //插入关键字
    tree->keynum ++;
    tree->key[pos] = key;
    tree->children[pos] = childPtr;
    if(childPtr != nullptr){
        childPtr->parent = tree;
    }
}

/**
 *
 * @param tree 被插入的树
 * @param key 插入的关键字
 * @param node 被插入的节点
 * @param pos 插入的位置
 */
void InsertBTree(BTree &tree, int key, BTree node, int pos) {

    int finished = false
    int needNewRoot = false ;


    //插入节点
    BTree partRight = nullptr;
    //第一次插入的情况
    if(node == nullptr) newRoot(tree, nullptr, nullptr,key);
        //后续插入的情况
    else{
        //完成插入退出循环,或则需要新建父亲节点的时候也退出循环
        while(!finished && !needNewRoot) {
            InsertAt(node, pos, key,partRight);
            if (node->keynum < order){
                finished = true;
            } //插入完成
            else { //分支数超过阶数,需要分裂结点
                int middle = (order + 1) / 2;
                //得到需要插入的key,并修改
                key = node->key[middle];
                splitTree(node, partRight, middle);
                //存在父亲节点,则尝试插入到父亲节点,否则需要新建父亲节点并插入
                if (node->parent != nullptr) {
                    //修改需要插入的节点为其父亲节点
                    node = node->parent;
                    //找到插入到父亲节点中的位置,并修改
                    pos = SearchKey(node, key);
                }else{
                    //父亲节点不存在,需要新建节点
                    needNewRoot = true;
                }
            }
        }

        //新建父亲节点,并连接其左右儿子
        if(needNewRoot){
            newRoot(tree,node,partRight,key);
        }
    }
}

void Insert(BTree &tree, int key) {
//    while(tree->children)
    Result *result = Search(tree, key);
    if (result->tag) return;
    InsertBTree(tree, key, result->tree, result->pos);
}

/**
 *
 * @param tree 树
 * @param layer 层级
 * 打印树
 * */
void printBTree(BTree tree, int layer){
    if(tree == nullptr ) return;

    char gap[layer*3]="" ;

    for(int i = 0 ; i < layer ;i++){
        strcat(gap,"   ");
    }

    if(layer > 0)
    printf("%s",gap);

    for(int i = 1 ; i <= tree->keynum ; i++){
        if(i == tree->keynum) {
            printf("%d", tree->key[i]);
        }else{
            printf ("%d,",tree->key[i] );
        }
    }


    layer++;
    printf("\n");
    for(int i = 0 ; i <= tree->keynum ; i++){
        printBTree(tree->children[i],layer);
    }
}

/**
 * 删除node节点中第pos个位置上的key,以及该位置右边的儿子
 * @param node
 * @param pos
 */
void RemoveRight(BTree &node, int pos){
    for(int i = pos ; i < node->keynum ; i++){
        node->children[i] = node->children[i+1];
        node->key[i] = node->key[i+1];
        node->children[i+1] = nullptr;
    }
    node->keynum --;
}

/**
 * 删除node节点中第pos个位置上的key,以及该位置左边的儿子
 * @param node
 * @param pos
 */
void RemoveLeft(BTree &node , int pos ){
    for(int i = pos ; i < node->keynum ; i++){
        node->children[i-1] = node->children[i];
        node->key[i] = node->key[i+1];
        node->children[i] = nullptr;
    }
    node->children[node->keynum-1] = node->children[node->keynum];
    node->children[node->keynum] = nullptr;
    node->keynum --;
}

/**
 * 将节点中所有内容 包括儿子指针向右移动一格
 * @param node
 */
void rightShift(BTree& node){
    for(int t = node->keynum ; t >= 0  ; t-- ){
        node->key[t+1] = node->key[t];
        node->children[t+1] = node->children[t];
    }
}
/**
 * 合并操作中,节点与右兄弟节点以及分割他们的父亲节点进行合并,并且合并到左兄弟
 * @param node 合并的节点
 * @param lchild 合并到的左兄弟
 * @param ppos  node节点在父亲节点中的第几个指针
 * @param tag 时候与根节点合并 1是0不是
 */
void CombineTreeToLeft(BTree& node, BTree &lchild , int ppos, int & tag){
    //得到分割两者的key
    int pKey = node->parent->key[ppos];
    //删除该key
    RemoveRight(node->parent, ppos);
    //该key加入左兄弟
    lchild->key[++lchild->keynum] = pKey;
    //节点并入左兄弟
    for(int i = 1 ; i <= node->keynum ; i++){
        lchild->children[lchild->keynum] = node ->children[i-1];
        lchild->key[++lchild->keynum] =  node->key[i] ;
        if(node->children[i-1] != nullptr)
        node ->children[i-1]->parent = lchild;
    }
    lchild->children[lchild->keynum] = node->children[node->keynum];
    if(node->children[lchild->keynum] != nullptr)
    node->children[node->keynum]->parent = lchild;

    if(node->parent->parent == nullptr && node->parent->keynum == 0 ) {
        tag = 1 ;
        lchild->parent = nullptr;
    }else{
        tag=  0 ;
    }
}

/**
 * 合并操作中,节点与右兄弟节点以及分割他们的父亲节点进行合并,并且合并刀右兄弟
 * @param node 合并的节点
 * @param rchild 合并到的右的右兄弟
 * @param ppos  node节点在父亲节点中的第几个指正
 * @param tag 时候与根节点合并 1是0不是
 */
void CombineTreeToRight(BTree& node,BTree &rchild , int ppos , int & tag ){
    int pKey = node->parent->key[ppos+1];
    RemoveLeft(node->parent,ppos+1);

    rightShift(rchild);

    rchild->key[1]= pKey;
    rchild->children[0] = node->children[node->keynum];
    if(node->children[node->keynum] != nullptr)
    node->children[node->keynum]->parent = rchild;
    rchild->keynum++;

    for(int i = node->keynum ; i  > 0 ; i--){
        rightShift(rchild);
        rchild->key[1] = node->key[i];
        rchild->children[0] = node->children[i-1];
        rchild->keynum ++;
        if(node->children[i-1] != nullptr)
        node->children[i-1]->parent = rchild;
    }

    if(node->parent->parent == nullptr  && node->parent->keynum == 0 ) {
        tag = 1 ;
        rchild->parent = nullptr;
    }else{
        tag=  0 ;
    }


}

void Restore(BTree &node ,BTree& btree ){
    int ppos = 0;
    BTree tree = node ;
    while(tree && tree != btree &&  tree->keynum < (order-1)/2) {

        for (int i = 0; i <= tree->parent->keynum; i++) {
            if (tree->parent->children[i] == tree) {
                ppos = i;
            }
        }

        //若左右兄弟有多的关键字,则转移.
        BTree lchild = nullptr;
        BTree rchild = nullptr;

        //父亲节点的ppos位置的指针指向本tree节点


        if (ppos > 0) {
            lchild = tree->parent->children[ppos - 1];
            //有左兄弟
        }
        if (ppos < tree->parent->keynum) {
            //有右兄弟
            rchild = tree->parent->children[ppos + 1];
        }

        //若转移后仍符合定义,则转移
        if (lchild && (lchild->keynum - 1) >= (order - 1) / 2) {
            //找左边的兄弟借,将双亲中最大节点下移到被删除的关键字, 找最大的关键节点 ,移动到双亲 ,
            rightShift(tree);

            tree->key[1] = tree->parent->key[ppos];
            tree->children[0] = lchild->children[lchild->keynum];
            if(tree->children[0])
            tree->children[0]->parent = tree;
            tree->parent->key[ppos] = lchild->key[lchild->keynum];
            RemoveRight(lchild, lchild->keynum);
            tree->keynum++;


        } else if (rchild && (rchild->keynum - 1) >= (order - 1) / 2) {
            //找右边的兄弟借,将双亲中最小节点下移到被删除的关键字, 找最小的关键节点 ,移动到双亲 ,

            tree->key[tree->keynum+1] = tree->parent->key[ppos+1];
            tree->parent->key[ppos+1] = rchild->key[1];
            tree->children[tree->keynum+1] = rchild->children[0];
            if( tree->children[tree->keynum+1])
            tree->children[tree->keynum+1]->parent = tree;
            RemoveLeft(rchild,1);
            tree->keynum++;
        } else {
            //兄弟没有多余关键字
            //如果有左兄弟,则与做兄弟合并/

            int tag  = 0;
            if (lchild) {
                CombineTreeToLeft(tree, lchild, ppos,tag );

                if(tag){
                    btree = lchild;
                    tree= lchild;
                }

            } else if (rchild) {
                CombineTreeToRight(tree, rchild, ppos,tag);
                if(tag){
                    btree = rchild;
                    tree = rchild;
                }
            }


        }

        tree = tree->parent;
    }
}

/**
 *
 * @param node 被删除节点
 * @param pos 删除的关键字在节点中的位置
 */

BTree Successor(BTree& node, int pos) {
    BTree min = node->children[pos];
    while(min->children[0] != nullptr){
        min = min->children[0];
    }
    int key = node->key[pos];
    node->key[pos] = min->key[1];
    min->key[1] = key;

    return min;
}


void DeleteBTree(BTree& btree , BTree &node , int pos ){
    //不是最下层非终端节点
    if(node->children[pos] != nullptr){
        BTree min = Successor(node,pos);
        DeleteBTree(btree,min,1);
    }else{
        //是最下层非终端节点
        RemoveRight(node, pos);
        //删除后不满足定义,即非终端节点至少含有[order/2]个子树
        // 即keynum +1 >=(order+1)/2
        if(node->keynum < (order-1)/2){
            Restore(node,btree);
        }
    }
}


int DeleteKeyInBTree(BTree &tree, int key ){
    Result* result = Search(tree,key);
    if(result->tag){
        DeleteBTree(tree,result->tree,result->pos);
        return true;
    }else{
        return false
    }

}


void middleorder(BTree tree,int& prePrintNum){
    for(int i = 0 ; i <= tree->keynum ; i++){
        if(tree->children[i])
            middleorder(tree->children[i],prePrintNum);

        if(i < tree->keynum) {
//            std:cout << tree->key[i + 1] << endl;
            if(prePrintNum > tree->key[i + 1] ) cout<< "error" << endl;
            prePrintNum = tree->key[i + 1];
        }
    }
}

int main() {

    BTree tree = nullptr;

//    int inserted[20];
      for(int i = 0 ; i < 5000 ; i ++){
          int t = i +1;
          Insert(tree,t);
//          inserted[i] = t;
      }

      for(int i = 1 ; i < 5000 ; i +=i){
//          cout << inserted[i] << endl;
          DeleteKeyInBTree(tree,i);
      }
    int pre = - 1;


    middleorder(tree,pre);

    printBTree(tree,0);


//     pre = - 1;
//    middleorder(tree,pre);
//
//    printBTree(tree,0);


}
