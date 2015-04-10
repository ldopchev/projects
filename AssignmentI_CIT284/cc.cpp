//********************************************************************************************************************
//
//									Lyubomir Dopchev - CIT284 Project I
//
//	This program creates a binary file creditCards.dat used for storing and cycling through account records, it also
//	creates Transactions.txt where the program appends each transaction. The transactins file has "columns account number",
//  Balance and Transaction Amount. Transaction Amount contains the amount of the transaction or the letter of the
//	new credit card created.
//	Most of the text manipulations are handled with c style strings.
//********************************************************************************************************************
#include <iostream>
#include <fstream>
#include <iomanip>
#include <cstdlib>
#include <conio.h>
#include <string>
#include <random>
#include <vector>


using namespace std;


const char *CREATE = "create";
const char *VERIFY = "verification";

fstream dataFileRead;
fstream dataFileWrite;
ofstream textFile;


// function declarations
char checkSymbol(char *argv);
void createAccount(char, char*);
char *randomNumberGenerator( int );
void openOutputFile();
void openInputFile();
void closeFile();
bool readFile();
void checkArgument(int argc, char **argv);
bool verifyAccount(char *accountNumber, char *transactionAmount);
void writeFile();
const int THREE = 3;
const int FOUR = 4;


struct Account {
	char accountNumber[17];
	double amount;
};

vector<Account> creditCardVector;

int main(int argc, char **argv){
	creditCardVector.clear();
	checkArgument(argc, argv);
	
	return 0;
}

//checks for credit card symbol typo
char checkSymbol(char *argv){
	if(_stricmp(argv, "ae")==0){
			return '3';
		}
		else if(_stricmp(argv, "v")==0){
			return '4';
		}
		else if(_stricmp(argv, "mc")==0){
			return '5';
		}
		else if(_stricmp(argv, "dis")==0){
			return '6';
		}
		else if(_stricmp(argv, "dine")==0){
			return '7';
		}
		else{
			cout<< "Invalid card symbol!\n"
				 <<	"Valid symbols are: AE, V, MC, DIS, DINE.\n"
				 << "Press a key...";
			_getch();
			return '8';					
		}
}

void createAccount(char firstDigit, char *creditCardSign){
	openInputFile();
	if(dataFileRead){
		readFile();
		dataFileRead.close();
		openOutputFile();
	}
	//if there is no inputfile, no transactions have been made, create an input file
	else if(!dataFileRead){
		dataFileRead.close();
		cout << "No file found, will create it for you....\n";
		openOutputFile();
		textFile << setw(20) << "Account number:" << setw(30) << "Balance:" <<setw(30) << "Transaction amount:" <<endl;
	}
	//set a character array to hold 16 chars representing the credit card digits
	char creditCardNumber[17];

	//set the initial character of the credit card
	char *firstDigitHandle = &firstDigit;
	char *firstThree, *secondFour, *thirdFour, *lastThree, lastDigit[2];

	firstThree = randomNumberGenerator(THREE);
	secondFour = randomNumberGenerator(FOUR);
	thirdFour = randomNumberGenerator(FOUR);
	lastThree = randomNumberGenerator(THREE);
	

	int lastDigitInt, secondFourInt, thirdFourInt, lastThreeInt;

	secondFourInt = atoi(secondFour);
	thirdFourInt = atoi(thirdFour);
	lastThreeInt = atoi(lastThree);

	lastDigitInt = (secondFourInt + thirdFourInt + lastThreeInt) % 11;
	if(lastDigitInt==10)
		lastDigitInt=0;
	_itoa(lastDigitInt, lastDigit, 10);

	//copy the firs character into subarray 0
	strcpy(creditCardNumber, firstDigitHandle);
	strcat(creditCardNumber, firstThree);
	strcat(creditCardNumber, secondFour);
	strcat(creditCardNumber, thirdFour);
	strcat(creditCardNumber, lastThree);
	strcat(creditCardNumber, lastDigit);
	
	
	//Create a new credit card account
	Account currentAccount = {"", 0.0};
	strcpy(currentAccount.accountNumber, creditCardNumber);
	
	//initial amount check
	if(lastDigitInt<=4)
		currentAccount.amount = 1000;
	else
		currentAccount.amount = 500;
	
	//write it to cc vector
	creditCardVector.push_back(currentAccount);
	
	cout << "Account created: " <<creditCardSign<< " "<< currentAccount.accountNumber << " Amount: " << currentAccount.amount << endl;
	
	//write to text file
	//*******************************************************************************************************************************************
	Account accountSubscript;
	accountSubscript = creditCardVector.at(creditCardVector.size()-1);
	textFile << setprecision(2) << fixed;
	textFile<< setw(20) << accountSubscript.accountNumber <<  setw(10) << "|" << setw(20) << accountSubscript.amount << setw(10) << "|" << setw(20) << creditCardSign << endl;
	//*******************************************************************************************************************************************

	//write to a binary file
	writeFile();
	dataFileWrite.close();
	textFile.close();
	
	
	getch();

}

char *randomNumberGenerator(const int offset){
	const int size = offset;
	char *digitArray = new char(offset+1);
	//char digitArray[size+1];
	int cardDigits;
	
	random_device generator;
	uniform_int_distribution<int> distribution(1, 9);
		
			for(int i = 0; i<offset; i++){
				char singleDigit[2];
				int cardDigits = distribution(generator);
				_itoa(cardDigits, singleDigit, 10);
				if(i==0)
					strcpy(digitArray, singleDigit);
				else
					strcat(digitArray, singleDigit);				
				
			}
			
		
		
		
		return digitArray;
}

void checkArgument(int argc, char **argv){
	if(argc<3 || argc>4){
		cout << "Invalid number of command line arguments!" << endl;
		cout << "Press a key...";
		
		_getch();
		exit (EXIT_FAILURE);
	}

	else if((_stricmp(*(argv+1), CREATE)==0) && argc == 3){
		
		char firstDigit = checkSymbol(*(argv+2));
		
		if(!(firstDigit=='8')){
			createAccount(firstDigit, *(argv+2));
		}
		else
			
			exit (EXIT_FAILURE);
	}
	else if((_stricmp(*(argv+1), VERIFY) == 0) && argc==4){
		if(!verifyAccount( *(argv+2), *(argv+3))){
			cout << "ACCOUNT NOT ON FILE" << endl;
			cout << "Try again..." << endl;
			
			_getch();
		}
	}

	else{
		cout << *(argv+1) << " is not a valid command! \n";
		cout << "Press a key...";
		_getch();
		
		exit (EXIT_FAILURE);
	}
}

bool verifyAccount(char *accountNumber, char *transactionAmount){
	openInputFile();
	if(dataFileRead){
		readFile();
		dataFileRead.close();
		openOutputFile();
	}
	else if(!dataFileRead){
		cout << "Error opening file....\n";
		exit(6);	}

	cout << transactionAmount << endl;
	float amount = atof(transactionAmount);

	
	bool check = false;
	
	for(int i = 0; i < creditCardVector.size(); i++){
		
		if(_stricmp(creditCardVector[i].accountNumber, accountNumber)==0){
			double accountAmount= creditCardVector[i].amount;
			accountAmount -=amount;
			if(accountAmount < 0){
				cout << "AUTHORIZATION DENIED" << endl;
				dataFileWrite.close();
				textFile.close();
				getch();
				exit(4);
			}
			
				cout << "AUTHORIZATION GRANTED" << endl;
				creditCardVector[i].amount = accountAmount;
				cout << creditCardVector[i].amount << endl;
				
				//write to text file
				//*******************************************************************************************************************************************
				Account accountSubscript;
				accountSubscript = creditCardVector[i];
				textFile << setprecision(2) << fixed;
				textFile<< setw(20) << accountSubscript.accountNumber <<  setw(10) << "|" 
					<< setw(20) << accountSubscript.amount << setw(10) << "|" 
					<< setw(20) << amount << endl;
				//*******************************************************************************************************************************************
				
				writeFile();
				dataFileWrite.close();
				textFile.close();
				getch();
				exit(EXIT_SUCCESS);
		}
	}
	
	return check;
}

void openInputFile(){
	
	dataFileRead.open("creditCards.dat", ios :: in | ios :: binary );

	if(dataFileRead.fail()){
		cout << "Error opening file...." << endl;
		
	}
}

void openOutputFile(){
	
	dataFileWrite.open("creditCards.dat", ios :: out | ios :: binary );
	
	//open output text file
	textFile.open("Transactions.txt", ios :: out | ios :: app);

	if(dataFileWrite.fail() || textFile.fail()){
		cout << "Error opening file..." << endl;
		
	}
}


bool readFile(){
	Account accountSubscript;
	
	cout << "In read file... " << endl;

	while(dataFileRead.read(reinterpret_cast<char *>(&accountSubscript), sizeof(accountSubscript))){
		//cout << "Account read: " << accountSubscript.accountNumber << "Amount: " << accountSubscript.amount << endl;			//use for debugging
		 creditCardVector.push_back(accountSubscript);
	}
	
	return false;
}

void writeFile(){
	
	//dataFileWrite.seekp(0L, ios::beg);
	for(int i = 0; i < creditCardVector.size(); i++){
		dataFileWrite.write(reinterpret_cast<char *>(&creditCardVector[i]), sizeof(creditCardVector[i]));
		//cout << "Account written: " << creditCardVector[i].accountNumber << "Amount: " << creditCardVector[i].amount << endl;		//use for debugging
	}
	
}
