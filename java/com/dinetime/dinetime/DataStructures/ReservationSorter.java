package com.dinetime.dinetime.DataStructures;

import com.dinetime.dinetime.Classes.Reservation;

import java.time.LocalDateTime;

public class ReservationSorter {

    public static void mergeSort(Reservation[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Reservation[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Reservation[] L = new Reservation[n1];
        Reservation[] R = new Reservation[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            LocalDateTime timeL = LocalDateTime.parse(L[i].getReservationDateTime());
            LocalDateTime timeR = LocalDateTime.parse(R[j].getReservationDateTime());

            if (!timeL.isAfter(timeR)) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
