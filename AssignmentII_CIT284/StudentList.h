#ifndef STUDENTLIST_H	
#define STUDENTLIST_H
#include "Student.h"

class StudentList{
private:
	struct ListNode{
		Student s;
		struct ListNode *next;
	};

	ListNode *head;

public:
	StudentList(){head = NULL;}

	~StudentList();

	//List functions
	void insertNode(Student);
	void displayList();
	
};
#endif // !LINKEDLIST_H
