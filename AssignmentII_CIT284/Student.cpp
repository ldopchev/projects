
#include "Student.h"
#include <random>
#include <cmath>
using namespace std;

void Student :: processFields(){
	int sum = 0;

					//set a random number from 0 to 100
					//and populate testScores array with true random numbers
	random_device generator;
	uniform_int_distribution<int> distribution(0, 100);
	for(int i = 0; i<10; i++){
		testScores[i] = distribution(generator);
		sum += testScores[i];
	}

	
	
					//calculate mean
	mean = (double)(sum/10);

					//calculate median
	double medDouble = (testScores[4]+testScores[3])/2.0;

					//round median add .5 and truncate it to an integer
	median = int(medDouble + 0.5);
	

	

					//copy the array into a second array and sort the second array
	int sortedScores[10];
	for(int i = 0; i < 10; i++){
		sortedScores[i] = testScores[i];
	}
					//sort the array with a bubble sort
	for(int i = 0; i < 9; ++i){
		for(int j = 0; j < i; ++j){
			if(sortedScores[j] > sortedScores[j+1]){
				int temp = sortedScores[j];
				sortedScores[j] = sortedScores[j+1];
				sortedScores[j+1] = temp;
			}
		}
	}

					//find the mode
	
	int count = 1;
	int max = 0;
	bool flag = false;
	//bool adjacent = false;

	for(int i = 0; i < 9; ++i){

		if(sortedScores[i] == sortedScores[i+1]){
			count++;
				if(count >= max){
					max = count;
					mode = sortedScores[i];
					flag = true;					
				}
				

		}
		else{
			count = 1; //reset count
			flag = false;
		}
		
						//after mode is found push it back into the empty modeVector
		if(flag && modeVector.empty())
			modeVector.push_back(mode);
		else if(flag &&  !modeVector.empty()){			
						//check if the last mode in the vector is the same
			if(modeVector[modeVector.size()-1]!=mode)
			modeVector.push_back(mode);
		}

	}// end of or loop

	//----------------------------------------------------------------------------------------------------
				//check if there is no mode, then mode is the average of all grades
	if(modeVector.size()==0){
		double sum = 0;
		for(int i = 0; i < 10; i ++){
			sum += sortedScores[i];
		}
		mode = sum/10.0;
		modeVector.push_back(mode);
	}

	//------------------------------------------------------------------------------------------------------
				//check if modes are adjacent
	bool adjacent = false;
	if(modeVector.size()>=2){			//ony if there are multiple modes
	for(int i = 0; i < modeVector.size()-1; i ++){
		for(int n = 0; n < 9; n ++){
			if(modeVector[i] == sortedScores[n] && modeVector[i+1] == sortedScores[n+1])
				adjacent = true;
				//cout << adjacent << endl;
		}
	}

	double sum = 0;	
	mode = 0;	//null mode value
	if(adjacent){
		for(int i = 0; i < modeVector.size(); i ++){
			sum += modeVector[i];
		}
				//calculate mode and delete vector elements
		mode = sum/(double)modeVector.size(); 
		modeVector.clear();
		modeVector.push_back(mode);
	}

	}



	//--------------------------------------------------------------------------------------------------
					//calculating standart deviation
	double squaredDifferenceSum = 0.0;

	for(int i = 0; i < 10; i++){
		double difference = testScores[i] - mean;
		double squaredDifference = pow(difference, 2.0);
		squaredDifferenceSum+= squaredDifference;
	}
	double meanSquared = squaredDifferenceSum/10.0;
	standardDeviation = sqrt(meanSquared);
}
