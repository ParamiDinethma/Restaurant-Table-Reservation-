package com.dinetime.dinetime.DataStructures;

import com.dinetime.dinetime.Classes.Reservation;

public class QueueArray {
    private Reservation[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.queue = new Reservation[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Reservation reservation) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        queue[rear] = reservation;
        size++;
    }

    public Reservation remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Reservation res = queue[front];
        front = (front + 1) % capacity;
        size--;
        return res;
    }

    public Reservation peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }

    public int getSize() {
        return size;
    }

    public Reservation[] getAllReservations() {
        Reservation[] result = new Reservation[size];
        for (int i = 0; i < size; i++) {
            result[i] = queue[(front + i) % capacity];
        }
        return result;
    }
}
