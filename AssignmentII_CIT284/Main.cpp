//-------------------------------------------------------------------------------------------------------------------------------
//									Lyubomir Dopchev CIT284 Project II
//-------------------------------------------------------------------------------------------------------------------------------

#include <iostream>
#include "Student.h"
#include "StudentList.h"
using namespace std;

const string F = "Freshman";
const string SM = "Sophmore";
const string J = "Junior";
const string S = "Senior";

const string LA = "Liberal Arts";
const string GA = "General Science";

//custom student linked lise
StudentList studentList;

//set an array of ten students 
Student students[10] = {Student(F, "John Mayers", LA), Student(J, "Scott Munson", GA), 
						Student(S, "Pete Mullen", GA), Student(SM, "Jess Sobietsky", LA),
						Student(SM, "Max Muller", GA), Student(F, "Niel Todd", LA),
						Student(S, "Rebecca Seig", LA), Student(J, "David Smith", GA),
						Student(F, "Leila Jones", LA), Student(SM, "Catherine Angels", LA)};

//function declarations
void populatelist();


int main(){
	populatelist();
	
	system("pause");
	return 0;
}

void populatelist(){
	for(int i = 0; i < 10; i++){
		studentList.insertNode(students[i]);
	}

	studentList.displayList();
}

