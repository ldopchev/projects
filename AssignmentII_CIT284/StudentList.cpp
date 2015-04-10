//------------------------------------------------------------------------
//				Implementation of linked list
//------------------------------------------------------------------------

#include "StudentList.h"
#include <iostream>
#include <iomanip>

using namespace std;

//------------------------------------------------------------------
//insertNode takes care of arranging the list by standard deviation
//------------------------------------------------------------------

void StudentList :: insertNode(Student s){
	ListNode *newNode;
	ListNode *nodePtr;		//to go through the list
	ListNode *previousNode = NULL;
	//allocate mem for new node
	newNode = new ListNode;
	newNode->s = s;
	

	if(!head){
		head = newNode;
		newNode->next = NULL;
	}
	else{
		nodePtr = head;
		previousNode = NULL;

		while(nodePtr!=NULL && nodePtr->s.getStandardDeviation() < s.getStandardDeviation()){
			previousNode = nodePtr;
			nodePtr = nodePtr->next;
		}
		
		if(previousNode == NULL){
			head = newNode;
			newNode->next = nodePtr;
		}
		else{
			previousNode->next = newNode;
			newNode->next = nodePtr;
		}
	}
}//end of insertNode()

void StudentList :: displayList(){
	ListNode * nodePtr;
	
	nodePtr = head;
	while(nodePtr){
		int * scoreArray;
		scoreArray = nodePtr->s.getTestScores();
		vector<double> modeVector = nodePtr ->s.getModeVector();

		cout << "***********************************************************************\n\n";
		cout << setw(20) << left << nodePtr->s.getName() << setw(20) << nodePtr->s.getMajor() << setw(20) << nodePtr->s.getStudentClass() << endl << endl;

		//cout << "***********************************************************************\n";
		cout << "\t\t\tTEST SCORES\n";
		//cout << "***********************************************************************\n";
		for(int i = 0; i < 10; i++){
			cout << "Test " << i+1 << ": " << *(scoreArray + i) <<"\t"; 
			if((i+1)%2==0)
				cout << endl;
		}
		cout << endl << endl;
		cout << setprecision(2) << fixed;
		cout << setw(20) << "STANDART DEVIATION" << setw(10) << "MEAN:" << setw(10) << "MEDIAN:" <<  endl;
		cout <<  setw(20) << nodePtr->s.getStandardDeviation() << setw(10) << nodePtr->s.getMean() <<setw(10) << nodePtr->s.getMedian() << setw(10) <<  endl;
		cout << endl;
		cout << setw(10) <<  "MODE" << endl;
		//display mode(s)
		
		for(int i = 0; i < modeVector.size(); i++){
			cout << setw(10) << modeVector[i] << "\t" ;
		}
		

		cout << endl << endl;

		nodePtr = nodePtr->next;
	}
}



StudentList :: ~StudentList(){
	ListNode *nodePtr;
	ListNode *nextNode;

	nodePtr = head;

	while(nodePtr != NULL){
		nextNode = nodePtr->next;
		delete nodePtr;

		nodePtr = nextNode;
	}
}
