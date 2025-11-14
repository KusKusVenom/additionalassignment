# additionalassignment
KMP String Matching Algorithm Implementation
Project Overview
This project implements the Knuth-Morris-Pratt (KMP) string matching algorithm in Java. KMP is an efficient pattern matching algorithm that searches for occurrences of a pattern within a text with linear time complexity.
Algorithm Description
What is KMP?
The Knuth-Morris-Pratt algorithm is a string-searching algorithm that searches for occurrences of a "pattern" within a main "text" by employing the observation that when a mismatch occurs, the pattern itself embodies sufficient information to determine where the next match could begin, thus bypassing re-examination of previously matched characters.
Key Features

Time Complexity: O(n + m) where n is the text length and m is the pattern length
Space Complexity: O(m) for the LPS (Longest Proper Prefix which is also Suffix) array
No Backtracking: The algorithm never goes backward in the text, making it highly efficient
Deterministic: Consistent performance regardless of input characteristics

Implementation Details
Core Components

LPS Array Construction (computeLPSArray)

Preprocesses the pattern to create an auxiliary array
Stores the length of the longest proper prefix which is also a suffix
Enables intelligent skipping during the search phase


Pattern Searching (search)

Uses the LPS array to avoid redundant comparisons
Returns all starting positions where the pattern is found
Efficiently handles multiple occurrences



Algorithm Workflow
1. Preprocess the pattern to compute LPS array
2. Initialize pointers for text (i) and pattern (j)
3. While not at end of text:
   a. If characters match, advance both pointers
   b. If full pattern matched, record position
   c. On mismatch, use LPS to skip characters
   d. If no LPS available, advance text pointer
4. Return all found positions
Test Cases
Test Case 1: Short String

Text: "ABABDABACDABABCABAB" (19 characters)
Pattern: "ABABC"
Purpose: Basic functionality test with simple pattern

Test Case 2: Medium String

Text: Repeating "ABCD" pattern (400 characters)
Pattern: "ABCDABCD"
Purpose: Test with multiple occurrences in regular pattern

Test Case 3: Long String

Text: Random DNA sequence (10,000 characters)
Pattern: "GCTAGCTA"
Purpose: Performance testing with large input
Note: Pattern inserted at known positions for verification

Test Case 4: No Match Scenario

Text: "AAAAAAAAAAA"
Pattern: "AAAB"
Purpose: Test behavior when pattern is not found

Test Case 5: Pattern at Boundaries

Text: "HELLO WORLD HELLO"
Pattern: "HELLO"
Purpose: Test pattern matching at start and end of text

How to Run
Prerequisites

Java Development Kit (JDK) 8 or higher
Command line or IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

Compilation
bashjavac KMPAlgorithm.java
Execution
bashjava KMPAlgorithm
Expected Output
The program will run all five test cases and display:

Text and pattern information
All positions where pattern is found
Execution time for each search
Comprehensive complexity analysis

Sample Output
KNUTH-MORRIS-PRATT (KMP) STRING MATCHING ALGORITHM
Implementation and Testing

### TEST CASE 1: SHORT STRING ###

================================================================================
SEARCH RESULTS
================================================================================
Text length: 19 characters
Pattern length: 5 characters
Pattern: "ABABC"

Text preview (first 100 chars): 
"ABABDABACDABABCABAB..."

Pattern found 1 time(s) at position(s):
  - Index 10: "...ACDABABCABAB..."

Execution time: 45231 nanoseconds (0.045231 ms)
================================================================================

[Additional test cases output...]

================================================================================
COMPLEXITY ANALYSIS
================================================================================

TIME COMPLEXITY:
  • Preprocessing (LPS computation): O(m) where m = pattern length
  • Searching: O(n) where n = text length
  • Overall: O(n + m)

SPACE COMPLEXITY:
  • LPS array: O(m)
  • Additional variables: O(1)
  • Overall: O(m)

ADVANTAGES:
  • Linear time complexity - very efficient
  • Never backtracks in the text
  • Optimal for single pattern matching
  • Deterministic performance (no worst-case degradation)

COMPARISON WITH NAIVE APPROACH:
  • Naive: O(n*m) worst case
  • KMP: O(n+m) always
  • Significant improvement for large texts
================================================================================
Complexity Analysis
Time Complexity

Preprocessing Phase: O(m)

Building the LPS array requires one pass through the pattern
Each character is processed at most twice


Search Phase: O(n)

Each character in the text is examined at most once
No backtracking in the text


Overall: O(n + m)

Linear time complexity makes it highly efficient
Significantly better than naive O(n*m) approach



Space Complexity

LPS Array: O(m)

Requires array of size equal to pattern length


Auxiliary Space: O(1)

Only a few integer variables needed


Overall: O(m)

Space usage is proportional to pattern length only



Performance Characteristics

Best Case: O(n + m) - when pattern doesn't match at all
Average Case: O(n + m) - consistent performance
Worst Case: O(n + m) - no degradation unlike naive approach

Comparison with Other Algorithms
AlgorithmTime ComplexitySpace ComplexityBest Use CaseNaiveO(n*m)O(1)Very short patternsKMPO(n + m)O(m)General purposeRabin-KarpO(n + m) avgO(1)Multiple patternsBoyer-MooreO(n/m) bestO(m + σ)Large patterns
Advantages of KMP

Guaranteed Linear Time: Always O(n + m), no worst-case degradation
No Backtracking: Never moves backward in the text
Simple to Implement: Clear logic with well-defined steps
Optimal for Streaming: Can process text as it arrives
Predictable Performance: Consistent execution time

Limitations

Preprocessing Required: Must build LPS array first
Single Pattern: Optimized for one pattern at a time
No Early Termination: For "first match only" scenarios, might be overkill

Real-World Applications

Text Editors: Find and replace functionality
DNA Sequence Analysis: Searching for genetic patterns
Intrusion Detection Systems: Pattern matching in network packets
Plagiarism Detection: Finding similar text passages
Data Compression: Finding repeated patterns

Author
Kuat
Course Information

Course: Design and Analysis of Algorithms
Assignment: String Algorithm Implementation
Date: November 2025

References

Knuth, Donald; Morris, James H.; Pratt, Vaughan (1977). "Fast pattern matching in strings"
Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). Introduction to Algorithms (3rd ed.)
Sedgewick, R., & Wayne, K. (2011). Algorithms (4th ed.)

License
This implementation is created for educational purposes as part of an academic assignment.

Troubleshooting
Issue: OutOfMemoryError with very large strings

Solution: Increase JVM heap size: java -Xmx2g KMPAlgorithm

Issue: Incorrect results

Solution: Verify that text and pattern are properly formatted
Check for null or empty strings
