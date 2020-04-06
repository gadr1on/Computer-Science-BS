#include <algorithm>
#include <iostream>
#include <iomanip>
#include <cassert>
#include <vector>
#include <set>
#include <cmath>
#include <fstream>
#include<stdexcept>
#include<climits>
using std::vector;
using std::pair;
using namespace std;
 
bool bVerbose = false;
int offset = 1; // offset value used for priting to screen, e.g. points start from 1 not 0
 
// Auxiliary functions
void dumpConnectedPointPairs(int point1, int point2,const string & filename=""){
  fstream outFile;
  outFile.open(filename,ios::out | ios::app);
  if (outFile.is_open()){
    outFile<<point1<<" "<<point2<<endl;
  }else {
    throw std::runtime_error("Could not open file");
  }
  outFile.close();
}
 
void displayVectorofPair(vector< pair<double, pair<int,int>> > & dvp,const string & str=""){
 
  size_t size= dvp.size();
  if (bVerbose){  cout<<"size= "<<size<<endl; }
 
  cout<<str<<endl;
  cout<<"Point 1 : ";
  for (auto pr : dvp){
    pair<int,int> point = pr.second;
    int firstpoint= point.first;
    cout<<firstpoint+offset<<' ';
  }
  cout<<"\nPoint 2 : ";
  for (auto pr : dvp){
    pair<int,int> point = pr.second;
    int secondpoint= point.second;
    cout<<secondpoint+offset<<' ';
  }
  cout<<"\nDistance: ";
  for (auto pr : dvp){
    double dist = pr.first;
    cout<<dist<<' ';
  }
  cout<<endl;
 
}
 
class DisjointSetsElement {
  public:
    int size, parent, rank;
 
    DisjointSetsElement(int size = 0, int parent = -1, int rank = 0):
      size(size), parent(parent), rank(rank) {}
};
 
class DisjointSets {
 public:
  int size;
  int max_table_size;
  vector <DisjointSetsElement> sets;
 
  explicit DisjointSets(int size):  size(size), max_table_size(0), sets(size) {
    for (int i = 0; i < size; i++){
		sets[i].parent = i; //at first parent is assigned as itself (self-parenting [self-loop])       
		sets[i].rank   = 0; // MY: Although already taken care of by the default value assignment. Done for the sake of clarity     
		}   }   
		int getParent(int i) { // similar to Find(). Find parent and compress path. [This will later allow log*()     
		if ( i != sets[i].parent) {  //time as the tree depth does not increase much by compression]       
		sets[i].parent = getParent(sets[i].parent );     }     
		return sets[i].parent ;   }   
		void merge(int i, int j){ // merging or union of two sets (union_rank) - this makes the algorithm log*() complexity     
			int i_id = getParent(i);     
			int j_id = getParent(j);     
			if (i_id == j_id)       
			return; 
	if ( sets[i_id].rank > sets[j_id].rank ){
      sets[j_id].parent = i_id;
    } else {
      sets[i_id].parent = j_id;
      if (sets[i_id].rank == sets[j_id].rank){
        sets[j_id].rank +=1;
      }
    }
  }
 
  // Print the disjoint set info for visualization/debugging
  void printSets(){ //print info on the sets
 
    cout<<"Vertex: ";
    for (int i=0; i<size; ++i){   cout<< i+offset <<' '; }
 
    cout<<"\nParent: ";
    for (auto &s : sets){ cout<< s.parent+offset <<' '; }
 
    cout<<"\nRank  : ";
    for (auto &s : sets){ cout<<s.rank<<' '; }
    cout<<endl;
  }
 
  // This is used later on decide on whether the number of clusters required by the user is achieved or not
  int findNumberofUniqueParents(){ //Not a classical disjoint set or Kruskal algorithm component
 
    std::set<int> local_set; //since sets can store unique elements, it is a natural choice here
    for (auto &s : sets){
      local_set.insert(s.parent);
    }
 
    if (bVerbose) {
      cout<<"Unique Number of Parents : "<<local_set.size()<<"  - Unique parent vertices: ";
      for (auto & s : local_set){
        cout<< s +offset<<' ';
      }
      cout<<endl;
    }
 
    return static_cast<int>(local_set.size());
  }
 
};
 
bool compare ( pair<double, pair<int,int> > & lhs, pair<double, pair<int,int> > & rhs){
  return lhs.first < rhs.first;
}
 
double clustering(vector<int> x, vector<int> y, int desiredNoClusters ) {
  // At the beginning, there are edges among all points, we make sure that we utilize edges only once (i.e. edge
  // between point 1 and point 2 is taken but not between Point 2 & Point 1.
  // Also self edges, i.e. Point i to Point i not included as it is zero
  size_t nVertex = x.size();
  vector<  pair<double,pair<int,int>>  > distVector; // A vector of pair where each pair stores the distance (cost)
                               // of each distinct edge and info of that edges on between which points it is as a pair if <int,int>
                               // e.g. let distance between Point j and k be 1.3, then a pair of < 1.3, pair<j,k> > will be pushed to vector
  for (size_t i=0; i<nVertex; ++i){
    for (size_t j=i+1; j<nVertex; ++j){
      double dist = sqrt( (x[i]-x[j])*(x[i]-x[j]) + (y[i]-y[j])*(y[i]-y[j]) );
 
      pair<int,int> point = make_pair(i,j);
      pair<double, pair<int,int> > distPoint = make_pair(dist,point);
 
      distVector.emplace_back(distPoint);
    }
  }
 
  if (bVerbose){ displayVectorofPair(distVector,"Dist Point Pair Vector:");   }
 
  // now sort the vector with respect to distance (in increasing distance order)
  std::sort(distVector.begin(), distVector.end(),compare); 
 
  if (bVerbose){ displayVectorofPair(distVector,"Dist Point Pair Vector (Sorted):"); }
 
  // For all vertices (points), make singleton sets and display for visualization
  DisjointSets allsets(nVertex); // makeSet() operation is done here
  if (bVerbose) {
      cout<<"\nAt the beginning, the sets of points"<<endl;
      allsets.printSets();
   }
 
  // delete the parent.txt file if it already exists
  std::remove("connectedpointpairs.txt");
 
  size_t inext=0;
  for (size_t i=0; i<distVector.size(); ++i){
 
    int point1 = (distVector[i].second).first;
    int point2 = (distVector[i].second).second;
    int parent1 = allsets.getParent(point1);
    int parent2 = allsets.getParent(point2);
 
    // Check whether they belong to the same parent. If so, that means we cannot merge
    // them since it would create a cycle which we don't want as we want minimum spanning tree
    bool sameParentsAlready = (parent1 == parent2);
    if (!sameParentsAlready ) { //do merge only if they are not connected already
 
      allsets.merge( point1, point2 ); // if they are not connected already (i.e. not have the same parents), merge them
      if (bVerbose) { allsets.printSets(); }
 
      // Dump the connection info between points after each merge (for visualization)
      dumpConnectedPointPairs(point1,point2,"connectedpointpairs.txt");
 
      // This is used to stop merging when the desired number of clusters are reached
      int currentNoClusters = allsets.findNumberofUniqueParents();
      if (currentNoClusters == desiredNoClusters ){
        inext = i+1;
        break;
      }
     }
  }
 
  // Now that we have reached to the desired number of clusters, but within the clusters there can be point pairs
  // where the distances are still smaller than those between the clusters. But because they would create cycles
  // in the tree, we sweep them until we reach to the distance that is really between the clusters and not creating
  // a cycle. At that point, we stop and that distance is the distance we are looking for.
  double finalDist =0.0 ;
  for (size_t i=inext; i<distVector.size(); ++i){
 
    int point1 = (distVector[i].second).first;
    int point2 = (distVector[i].second).second;
    int parent1 = allsets.getParent(point1);
    int parent2 = allsets.getParent(point2);
 
    bool sameParentsAlready = (parent1 == parent2);
    if (sameParentsAlready ) {
      allsets.merge( point1, point2 );
     } else {
      finalDist = distVector[i].first;
      break;
     }
  }
 
  cout<<"Final minimum Distance between clusters = "<<finalDist<<endl;
 
  return finalDist;
} 
 
int main(int argc, char** argv) {
 
  for (int i=0; i<argc;++i){
    string str1=argv[i];
    if (str1.compare("-verbose") == 0){
      cout<<"Verbose option is requested"<<endl;       bVerbose = true;     }   }   size_t n;   int k;   std::cin >> n;
  vector<int> x(n), y(n);
  for (size_t i = 0; i < n; i++) {     std::cin >> x[i] >> y[i];
  }
  std::cin >> k;
  std::cout << std::setprecision(10) << clustering(x, y, k) << std::endl;
}
