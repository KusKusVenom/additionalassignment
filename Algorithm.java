/**
 * KMP String Matching Algorithm
 * @author Kuat Bereketov
 * @date November 14 2025
 */

import java.util.*;

public class KMPAlgorithm {
    
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        
        // Length of the previous longest prefix suffix
        int len = 0;
        lps[0] = 0; // LPS of first character is always 0
        
        int i = 1;
        // Calculate lps[i] for i = 1 to m-1
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                // Characters match, increment length and store in lps
                len++;
                lps[i] = len;
                i++;
            } else {
                // Mismatch after len matches
                if (len != 0) {
                    // Try the previous longest prefix suffix
                    len = lps[len - 1];
                    // Don't increment i here, we'll compare again
                } else {
                    // No prefix suffix exists
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    public static List<Integer> search(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        
        // Edge cases
        if (pattern == null || pattern.isEmpty()) {
            return occurrences;
        }
        if (text == null || text.length() < pattern.length()) {
            return occurrences;
        }
        
        int n = text.length();
        int m = pattern.length();
        
        // Preprocessing: Compute LPS array
        int[] lps = computeLPSArray(pattern);
        
        // Matching phase
        int i = 0; // index for text
        int j = 0; // index for pattern
        
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                // Characters match, move both pointers
                i++;
                j++;
            }
            
            if (j == m) {
                // Pattern found at index (i - j)
                occurrences.add(i - j);
                // Continue searching for more occurrences
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // Mismatch after j matches
                if (j != 0) {
                    // Use LPS to skip characters intelligently
                    j = lps[j - 1];
                } else {
                    // No match at all, move to next character in text
                    i++;
                }
            }
        }
        
        return occurrences;
    }
    
    private static void displayResults(String text, String pattern, List<Integer> occurrences, long timeTaken) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SEARCH RESULTS");
        System.out.println("=".repeat(80));
        System.out.println("Text length: " + text.length() + " characters");
        System.out.println("Pattern length: " + pattern.length() + " characters");
        System.out.println("Pattern: \"" + pattern + "\"");
        System.out.println("\nText preview (first 100 chars): ");
        System.out.println("\"" + text.substring(0, Math.min(100, text.length())) + "...\"\n");
        
        if (occurrences.isEmpty()) {
            System.out.println("Pattern not found in text.");
        } else {
            System.out.println("Pattern found " + occurrences.size() + " time(s) at position(s):");
            for (int pos : occurrences) {
                System.out.println("  - Index " + pos + ": \"..." + 
                    text.substring(Math.max(0, pos - 5), Math.min(text.length(), pos + pattern.length() + 5)) + 
                    "...\"");
            }
        }
        
        System.out.println("\nExecution time: " + timeTaken + " nanoseconds (" + 
            (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("=".repeat(80));
    }
    
    public static void main(String[] args) {
        System.out.println("KNUTH-MORRIS-PRATT (KMP) STRING MATCHING ALGORITHM");
        System.out.println("Implementation and Testing\n");
        
        System.out.println("\n### TEST CASE 1: SHORT STRING ###");
        String text1 = "ABABDABACDABABCABAB";
        String pattern1 = "ABABC";
        
        long startTime = System.nanoTime();
        List<Integer> result1 = search(text1, pattern1);
        long endTime = System.nanoTime();
        
        displayResults(text1, pattern1, result1, endTime - startTime);
        
        System.out.println("\n### TEST CASE 2: MEDIUM STRING ###");
        String text2 = generateRepeatingText("ABCD", 100); // 400 characters
        String pattern2 = "ABCDABCD";
        
        startTime = System.nanoTime();
        List<Integer> result2 = search(text2, pattern2);
        endTime = System.nanoTime();
        
        displayResults(text2, pattern2, result2, endTime - startTime);
        
        System.out.println("\n### TEST CASE 3: LONG STRING ###");
        String text3 = generateDNASequence(10000); // 10,000 characters
        String pattern3 = "GCTAGCTA";
        
        startTime = System.nanoTime();
        List<Integer> result3 = search(text3, pattern3);
        endTime = System.nanoTime();
        
        displayResults(text3, pattern3, result3, endTime - startTime);
        
        System.out.println("\n### TEST CASE 4: NO MATCH SCENARIO ###");
        String text4 = "AAAAAAAAAAA";
        String pattern4 = "AAAB";
        
        startTime = System.nanoTime();
        List<Integer> result4 = search(text4, pattern4);
        endTime = System.nanoTime();
        
        displayResults(text4, pattern4, result4, endTime - startTime);
        
        System.out.println("\n### TEST CASE 5: PATTERN AT BOUNDARIES ###");
        String text5 = "HELLO WORLD HELLO";
        String pattern5 = "HELLO";
        
        startTime = System.nanoTime();
        List<Integer> result5 = search(text5, pattern5);
        endTime = System.nanoTime();
        
        displayResults(text5, pattern5, result5, endTime - startTime);
        
        printComplexityAnalysis();
    }
  
    private static String generateRepeatingText(String base, int repetitions) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repetitions; i++) {
            sb.append(base);
        }
        return sb.toString();
    }
    
    private static String generateDNASequence(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random(42); // Fixed seed for reproducibility
        char[] nucleotides = {'A', 'C', 'G', 'T'};
        
        for (int i = 0; i < length; i++) {
            sb.append(nucleotides[random.nextInt(4)]);
        }
        
  
        int pos1 = length / 4;
        int pos2 = length / 2;
        String pattern = "GCTAGCTA";
        
        sb.replace(pos1, pos1 + pattern.length(), pattern);
        sb.replace(pos2, pos2 + pattern.length(), pattern);
        
        return sb.toString();
    }
    
    
    private static void printComplexityAnalysis() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("=".repeat(80));
        System.out.println("\nTIME COMPLEXITY:");
        System.out.println("  • Preprocessing (LPS computation): O(m) where m = pattern length");
        System.out.println("  • Searching: O(n) where n = text length");
        System.out.println("  • Overall: O(n + m)");
        System.out.println("\nSPACE COMPLEXITY:");
        System.out.println("  • LPS array: O(m)");
        System.out.println("  • Additional variables: O(1)");
        System.out.println("  • Overall: O(m)");
        System.out.println("\nADVANTAGES:");
        System.out.println("  • Linear time complexity - very efficient");
        System.out.println("  • Never backtracks in the text");
        System.out.println("  • Optimal for single pattern matching");
        System.out.println("  • Deterministic performance (no worst-case degradation)");
        System.out.println("\nCOMPARISON WITH NAIVE APPROACH:");
        System.out.println("  • Naive: O(n*m) worst case");
        System.out.println("  • KMP: O(n+m) always");
        System.out.println("  • Significant improvement for large texts");
        System.out.println("=".repeat(80));
    }
}
