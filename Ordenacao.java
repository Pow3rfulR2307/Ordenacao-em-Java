package com.mycompany.ordenacao;

import java.util.Random;

/**
 *
 * @author pedro
 */
public class Ordenacao {
    
    public static double Heapsort(int[] vetor, int vecSize, int trocas, int iteracoes, int loopi,
            long startSort, long finishSort){
  
        startSort = System.nanoTime();
        
        for (int i = vecSize / 2 - 1; i >= 0; i--){ 
            
            trocas = heapify(vetor, vecSize, i,trocas);
            iteracoes+=1;
        }
 
        for (int i = vecSize - 1; i > 0; i--) { 
            
            int troca = vetor[0]; 
            vetor[0] = vetor[i];
            vetor[i] = troca;
                 
            trocas = heapify(vetor, i, 0, trocas); 
            iteracoes+=1;
        }
        finishSort = System.nanoTime() - startSort;
        
        if(loopi == 0){
            System.out.println("HeapSort com "+vecSize+" valores || trocas = "+trocas+" -> iteracoes = "+iteracoes);      
        //for(int a:vetor){System.out.println(a);}
        }
        
        return (double)finishSort/1000000; 
    }
 
    public static int heapify(int[] vetor, int N, int i, int trocas)
    {
        int largest = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
 
        if (esq < N && vetor[esq] > vetor[largest]) 
            largest = esq;
 
        if (dir < N && vetor[dir] > vetor[largest]) 
            largest = dir;
 
        if (largest != i) { 
            
            int troca = vetor[i];
            vetor[i] = vetor[largest];
            vetor[largest] = troca;
 
            trocas+=1;
            
            heapify(vetor, N, largest, trocas);
        }
        //trocas+=1;
        return trocas;
    }
    //------------------------------
    
    public static double shellSort(int[]vetor, int vecSize, int trocas, int iteracoes, long startSort, long finishSort, int loopi) { 
        
        startSort = System.nanoTime();
        for (int gap = vecSize/2; gap > 0; gap /= 2) {           
            for (int i = gap; i < vecSize; i ++) { 

                int temp = vetor[i];   
                int j;
                for (j = i; j >= gap && vetor[j-gap] > temp; j -= gap){
                
                    vetor[j] = vetor[j-gap];
                    trocas+=1;
                    //iteracoes+=1;
                }
                iteracoes+=1;
                vetor[j] = temp; 
            } 
        }
        finishSort = System.nanoTime() - startSort;
        if(loopi == 0){
            
            System.out.println("ShellSort com "+vecSize+" valores || trocas = "+trocas+" -> iteracoes = "+iteracoes);
        
        //for(int a:vetor){System.out.println(a);}
        }
        return (double)finishSort/1000000; 
    } 

    public static double bubbleSort(int[]vetor, int vecSize, int trocas, int iteracoes, long startSort, long finishSort, int loopi){
    
        int temp;      
        startSort = System.nanoTime();
        for(int i =0; i < vecSize; i++){ //valor do size
        
            for(int j = 1; j < (vecSize - 1); j++){ //valor do size-2
                
                if(vetor[j-1] > vetor[j]){
                
                    temp = vetor[j-1];
                    vetor[j-1] = vetor[j];
                    vetor[j] = temp;    
                    trocas+=1;
                }               
                iteracoes+=1;
            }
        }
        finishSort = System.nanoTime() - startSort;
        if(loopi == 0){
            System.out.println("BubbleSort com "+vecSize+" valores || trocas = "+trocas+" -> iteracoes = "+iteracoes);      
        //for(int a:vetor){System.out.println(a);}
        }
        return (double)finishSort/1000000;
    }
    
    public static void main(String[] args) {
        
        Random valor = new Random(69);
        int[] sizeVector = {50, 500, 1000, 5000, 10000};
        int trocas = 0;
        int iteracoes = 0;
        long startSort = 0;
        long finishSort = 0;
        float averageBubble = 0;
        float averageShell = 0;
        float averageHeap = 0;
        
        for(int size : sizeVector){
                        
            int[] vectorInt = new int[size];
            
            for(int i = 0; i < size; i++){
            
                int aleatorio = valor.nextInt(999999 - 1 + 1) + 1;
                
                vectorInt[i] = aleatorio;
            }            
            //for(int i : vectorInt){System.out.println(i);}            
            for(int i = 0; i < 5; i++){
            
             averageBubble += bubbleSort(vectorInt, size, trocas, iteracoes, startSort, finishSort, i);
             averageShell += shellSort(vectorInt, size, trocas, iteracoes, startSort, finishSort, i);
             averageHeap += Heapsort(vectorInt, size, trocas, iteracoes, i, startSort, finishSort);
            }
             
            System.out.println("\nMedia de tempo de execucao do BubbleSort com "+size+" valores: "+averageBubble/5);
            System.out.println("Media de tempo de execucao do ShellSort com "+size+" valores: "+averageShell/5);
            System.out.println("Media de tempo de execucao do HeapSort com "+size+" valores: "+averageHeap/5+"\n");
            
            averageBubble = 0;
            averageShell = 0;
            averageHeap = 0;
            trocas = 0;
            iteracoes = 0;
        }
    }
}
