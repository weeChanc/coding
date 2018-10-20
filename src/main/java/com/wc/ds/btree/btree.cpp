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
 * @param tree ���Ľڵ�
 * @param key  ��ѯ��key
 * @return ��ѯ��key�ڸýڵ���key�����λ��
 */
int SearchKey(BTree tree, int key) {
    int i = 1;
    while (i <= tree->keynum && key > tree->key[i]) i++;
    return i;
}

/**
 *
 * @param tree ��ѯ����
 * @param key ��ѯ�Ĺؼ���
 * @return ���ز�ѯ���Ľ���ṹ��
 */
Result *Search(BTree tree, int key) {
    Result *result = (Result *) malloc(sizeof(Result));
    BTree cur = tree, pre = nullptr;
    int pos = 0;
    int found = false;

    while (cur != nullptr && !found) {
        //��ѯkey�ڸýڵ���key�����λ��
        pos = SearchKey(cur, key);
        //�ҵ���λ�� ��found�����1,��λ�ñ�ʾ��key���ڵ�λ�� , �����λ�ý���ʾ�����λ��
        if (pos <= cur->keynum && cur->key[pos] == key) found = 1;
        else {
            pre = cur;
            //����ָ��
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
 * @param tree �½��ĸ��ڵ�
 * @param left ���ڵ�ĵ�һ������
 * @param right ���ڵ�ĵڶ�������
 * @param key ���ڵ��key
 * ��left�� right�� ����һ�� �� key Ϊ�� ����
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
 * @param tree �������ѵ��� , �����Ƿֳ����󲿷�
 * @param partRight ���ѳ����Ҳ���
 * @param pos �Ը�λ�ÿ�ʼ����
 *
 *
 */
void splitTree(BTree &tree , BTree &partRight , int pos){

    partRight = (BTree)malloc(sizeof(BTNode));

    //�����Ҳ���
    partRight->children[0] = tree->children[pos];
    for(int i = 1 , j = pos+1 ; j <= tree->keynum ; j++,i++){
        partRight->children[i] = tree->children[j];
        partRight->key[i] = tree->key[j];
    }

    //���ѵ�ʱ���󲿷��Ա����븸�׽ڵ������,���Ҳ��ָ����ѳ�ȥ,��Ҫ���½��븸�׽ڵ���
    partRight->parent = tree->parent;
    partRight->keynum = tree->keynum - pos;
    //�޸��½ڵ�Ķ��ӵ�˫�׽ڵ�
    for(int i = 0 ; i <= partRight->keynum ; i++){
        if( partRight->children[i]) {
            partRight->children[i]->parent = partRight;
        }
    }

    //��װɾ����ԭ���е��Ҳ���
    tree->keynum = pos -1;
    //��ɾ��
    for( int i = tree->keynum ; i < order ; i ++){

    }


}

/**
 *
 * @param tree ����ýڵ�Ϊ������,ֻ���뵽�ø���
 * @param pos �����λ��
 * @param key �����key
 * @param childPtr ����������ӵĶ�����ָ��
 */
void InsertAt(BTree &tree , int pos , int key,BTree childPtr ){

    //POS��POS���������

    for(int i = tree->keynum ; i >= pos ; i--){
        tree->key[i+1] = tree->key[i];
        tree->children[i+1] = tree->children[i];
    }

    //����ؼ���
    tree->keynum ++;
    tree->key[pos] = key;
    tree->children[pos] = childPtr;
    if(childPtr != nullptr){
        childPtr->parent = tree;
    }
}

/**
 *
 * @param tree ���������
 * @param key ����Ĺؼ���
 * @param node ������Ľڵ�
 * @param pos �����λ��
 */
void InsertBTree(BTree &tree, int key, BTree node, int pos) {

    int finished = false
    int needNewRoot = false ;


    //����ڵ�
    BTree partRight = nullptr;
    //��һ�β�������
    if(node == nullptr) newRoot(tree, nullptr, nullptr,key);
        //������������
    else{
        //��ɲ����˳�ѭ��,������Ҫ�½����׽ڵ��ʱ��Ҳ�˳�ѭ��
        while(!finished && !needNewRoot) {
            InsertAt(node, pos, key,partRight);
            if (node->keynum < order){
                finished = true;
            } //�������
            else { //��֧����������,��Ҫ���ѽ��
                int middle = (order + 1) / 2;
                //�õ���Ҫ�����key,���޸�
                key = node->key[middle];
                splitTree(node, partRight, middle);
                //���ڸ��׽ڵ�,���Բ��뵽���׽ڵ�,������Ҫ�½����׽ڵ㲢����
                if (node->parent != nullptr) {
                    //�޸���Ҫ����Ľڵ�Ϊ�丸�׽ڵ�
                    node = node->parent;
                    //�ҵ����뵽���׽ڵ��е�λ��,���޸�
                    pos = SearchKey(node, key);
                }else{
                    //���׽ڵ㲻����,��Ҫ�½��ڵ�
                    needNewRoot = true;
                }
            }
        }

        //�½����׽ڵ�,�����������Ҷ���
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
 * @param tree ��
 * @param layer �㼶
 * ��ӡ��
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
 * ɾ��node�ڵ��е�pos��λ���ϵ�key,�Լ���λ���ұߵĶ���
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
 * ɾ��node�ڵ��е�pos��λ���ϵ�key,�Լ���λ����ߵĶ���
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
 * ���ڵ����������� ��������ָ�������ƶ�һ��
 * @param node
 */
void rightShift(BTree& node){
    for(int t = node->keynum ; t >= 0  ; t-- ){
        node->key[t+1] = node->key[t];
        node->children[t+1] = node->children[t];
    }
}
/**
 * �ϲ�������,�ڵ������ֵܽڵ��Լ��ָ����ǵĸ��׽ڵ���кϲ�,���Һϲ������ֵ�
 * @param node �ϲ��Ľڵ�
 * @param lchild �ϲ��������ֵ�
 * @param ppos  node�ڵ��ڸ��׽ڵ��еĵڼ���ָ��
 * @param tag ʱ������ڵ�ϲ� 1��0����
 */
void CombineTreeToLeft(BTree& node, BTree &lchild , int ppos, int & tag){
    //�õ��ָ����ߵ�key
    int pKey = node->parent->key[ppos];
    //ɾ����key
    RemoveRight(node->parent, ppos);
    //��key�������ֵ�
    lchild->key[++lchild->keynum] = pKey;
    //�ڵ㲢�����ֵ�
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
 * �ϲ�������,�ڵ������ֵܽڵ��Լ��ָ����ǵĸ��׽ڵ���кϲ�,���Һϲ������ֵ�
 * @param node �ϲ��Ľڵ�
 * @param rchild �ϲ������ҵ����ֵ�
 * @param ppos  node�ڵ��ڸ��׽ڵ��еĵڼ���ָ��
 * @param tag ʱ������ڵ�ϲ� 1��0����
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

        //�������ֵ��ж�Ĺؼ���,��ת��.
        BTree lchild = nullptr;
        BTree rchild = nullptr;

        //���׽ڵ��pposλ�õ�ָ��ָ��tree�ڵ�


        if (ppos > 0) {
            lchild = tree->parent->children[ppos - 1];
            //�����ֵ�
        }
        if (ppos < tree->parent->keynum) {
            //�����ֵ�
            rchild = tree->parent->children[ppos + 1];
        }

        //��ת�ƺ��Է��϶���,��ת��
        if (lchild && (lchild->keynum - 1) >= (order - 1) / 2) {
            //����ߵ��ֵܽ�,��˫�������ڵ����Ƶ���ɾ���Ĺؼ���, �����Ĺؼ��ڵ� ,�ƶ���˫�� ,
            rightShift(tree);

            tree->key[1] = tree->parent->key[ppos];
            tree->children[0] = lchild->children[lchild->keynum];
            if(tree->children[0])
            tree->children[0]->parent = tree;
            tree->parent->key[ppos] = lchild->key[lchild->keynum];
            RemoveRight(lchild, lchild->keynum);
            tree->keynum++;


        } else if (rchild && (rchild->keynum - 1) >= (order - 1) / 2) {
            //���ұߵ��ֵܽ�,��˫������С�ڵ����Ƶ���ɾ���Ĺؼ���, ����С�Ĺؼ��ڵ� ,�ƶ���˫�� ,

            tree->key[tree->keynum+1] = tree->parent->key[ppos+1];
            tree->parent->key[ppos+1] = rchild->key[1];
            tree->children[tree->keynum+1] = rchild->children[0];
            if( tree->children[tree->keynum+1])
            tree->children[tree->keynum+1]->parent = tree;
            RemoveLeft(rchild,1);
            tree->keynum++;
        } else {
            //�ֵ�û�ж���ؼ���
            //��������ֵ�,�������ֵܺϲ�/

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
 * @param node ��ɾ���ڵ�
 * @param pos ɾ���Ĺؼ����ڽڵ��е�λ��
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
    //�������²���ն˽ڵ�
    if(node->children[pos] != nullptr){
        BTree min = Successor(node,pos);
        DeleteBTree(btree,min,1);
    }else{
        //�����²���ն˽ڵ�
        RemoveRight(node, pos);
        //ɾ�������㶨��,�����ն˽ڵ����ٺ���[order/2]������
        // ��keynum +1 >=(order+1)/2
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
