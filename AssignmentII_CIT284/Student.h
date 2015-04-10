#ifndef STUDENT_H
#define STUDENT_H
#include <string>
#include <vector>
#include <iostream>

using namespace std;
class Student{
private:
	string name;
	string studentClass;
	string major;
	int testScores[10];
	
	double mean;
	int median;
	double mode;
	double standardDeviation;
	vector<double> modeVector;    //holds multiple modes

public:
	Student(){}

	Student(string sClass, string sName,string sMajor){
		name = sName;
		studentClass = sClass;
		major = sMajor;

		//process the rest of the attributes
		//
		processFields();
	}

					//populate testScores array
					//calculate mean, median, mode and  standard deviation
					//
	void processFields();
	void calculateMean();

					//setter Methods
	void setStudentClass(string sClass){studentClass = sClass;}
	void setMajor(string sMajor){major = sMajor;}
	void setName(string sName){name = sName;}
	

					//getter methods
	string getName(){return name;}
	string getStudentClass(){return studentClass;}
	string getMajor(){return major;}
	int * getTestScores(){return testScores;}
	double getMean(){return mean;}
	vector<double> getModeVector(){return modeVector;}
	double getMedian(){return median;}
	double getMode(){return mode;}
	double getStandardDeviation(){return standardDeviation;}
	
};

#endif