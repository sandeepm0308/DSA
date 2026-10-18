# Interactive Visualization of Singly, Doubly and Circular Linked Lists Using Command-Line Interface

**Data Structures and Algorithms (DSA) Mini Project**  
**Academic Level:** 2nd-Year Computer Science & Engineering  
**Language:** Java (Pure standard Java SE, zero external dependencies)  
**🌐 Live Web Visualizer:** [https://web-rho-umber-pqjmwme1zj.vercel.app](https://web-rho-umber-pqjmwme1zj.vercel.app)

---

## 1. Project Overview & Abstract

In computer science education, understanding linked data structures is challenging when students cannot directly observe pointer manipulations, memory references, and boundary edge conditions. 

This project provides an interactive, terminal-based visualizer for three core linked list variants:
1. **Singly Linked List (SLL)**
2. **Doubly Linked List (DLL)**
3. **Circular Linked List (CLL)**

Every operation dynamically renders ASCII diagrams showing `HEAD`, `TAIL`, node values, pointer directions (`->`, `<-`, `<->`), boundary termination (`NULL`), and circular wrap-around loops (`+---+`).

---

## 2. Project Directory Structure

```text
DSA/
├── src/
│   └── com/
│       └── dsa/
│           └── linkedlist/
│               ├── Node.java               # Singly & Circular Node (data, next)
│               ├── DNode.java              # Doubly Node (data, prev, next)
│               ├── SinglyLinkedList.java   # SLL logic & ASCII visualizer
│               ├── DoublyLinkedList.java   # DLL logic & bidirectional visualizer
│               ├── CircularLinkedList.java # CLL logic & loop visualizer
│               ├── VisualizerUtils.java    # ANSI color, banner, and formatting helpers
│               └── Main.java               # Menu controller, CLI parser, 5 demo scenarios
├── sample_inputs/
│   ├── scenario1_singly.txt               # Input script for SLL full lifecycle
│   ├── scenario2_doubly.txt               # Input script for DLL bidirectional ops
│   ├── scenario3_circular.txt             # Input script for CLL wrap-around ops
│   ├── scenario4_error_handling.txt       # Input script for edge-cases & underflow
│   └── scenario5_scheduler.txt            # Input script for Round-Robin process queue
├── run.bat                                # 1-click Windows compilation & run script
├── run.sh                                 # 1-click Linux / macOS bash script
├── open_web.bat                           # 1-click Local browser & Public sharing launcher
├── share_public.bat                       # Worldwide HTTPS tunnel link generator
├── web/                                   # Standalone interactive web visualizer
│   ├── index.html                         # Side-by-side visualizer layout & C code stepper
│   ├── style.css                          # Modern dark glassmorphic UI design
│   └── app.js                             # Interactive pointer animation & code engine
└── README.md                              # Comprehensive project documentation & Viva Q&A
```

---

## 3. Supported Operations & Time Complexities

| Operation | Singly Linked List | Doubly Linked List | Circular Linked List | Description |
| :--- | :---: | :---: | :---: | :--- |
| **`create`** | $O(n)$ | $O(n)$ | $O(n)$ | Populates list from an array of elements |
| **`insert_begin`** | $O(1)$ | $O(1)$ | $O(1)$ | Adds a new node at position 1 (head) |
| **`insert_end`** | $O(n)$ | $O(1)$ *(tail)* | $O(1)$ *(tail)* | Adds a new node at the end |
| **`insert_pos`** | $O(n)$ | $O(n)$ | $O(n)$ | Inserts at any 1-based index (1 to size + 1) |
| **`delete_begin`** | $O(1)$ | $O(1)$ | $O(1)$ | Deletes the head node |
| **`delete_end`** | $O(n)$ | $O(1)$ *(tail)* | $O(n)$ *(traverse)* | Deletes the tail node |
| **`delete_pos`** | $O(n)$ | $O(n)$ | $O(n)$ | Deletes node at 1-based index (1 to size) |
| **`search`** | $O(n)$ | $O(n)$ | $O(n)$ | Finds element and reports positions |
| **`reverse`** | $O(n)$ | $O(n)$ | N/A | In-place pointer reversal ($O(1)$ extra space) |
| **`display`** | $O(n)$ | $O(n)$ | $O(n)$ | Renders terminal ASCII diagram |
| **`display_backward`**| N/A | $O(n)$ | N/A | Backward traversal using `prev` pointers |
| **`count`** | $O(1)$ | $O(1)$ | $O(1)$ | Returns total active node count |

*Space Complexity for all lists:* $O(n)$ where $n$ is the number of nodes.

---

## 4. Visual Representation Reference

### Singly Linked List
```text
   HEAD                                                        TAIL
     v                                                           v
  [5 | *] -> [10 | *] -> [20 | *] -> [25 | *] -> [30 | *] -> [40 | *] -> NULL
  (Pos 1)    (Pos 2)     (Pos 3)     (Pos 4)     (Pos 5)     (Pos 6)
  Traversal: 5 -> 10 -> 20 -> 25 -> 30 -> 40 -> NULL
  Metadata : Size = 6 | Head = 5 | Tail = 40
```

### Doubly Linked List
```text
  HEAD                                        TAIL
    v                                           v
  NULL <- [ 50 ] <-> [ 100 ] <-> [ 200 ] <-> [ 300 ] <-> [ 400 ] -> NULL
          (Pos 1)    (Pos 2)     (Pos 3)     (Pos 4)     (Pos 5)
  Forward  : 50 <-> 100 <-> 200 <-> 300 <-> 400 <-> NULL
  Metadata : Size = 5 | Head = 50 | Tail = 400
```

### Circular Linked List
```text
            +-----------------------------------------------+
            v                                               |
  HEAD -> [10] -> [20] -> [25] -> [30] -> [40] -> [50] -----+
          (Pos 1) (Pos 2) (Pos 3) (Pos 4) (Pos 5) (Pos 6 TAIL)
  Circular Traversal: 10 -> 20 -> 25 -> 30 -> 40 -> 50 -> (loops back to 10)
  Metadata: Size = 6 | Head = 10 | Tail = 50 | Tail.next = 10 (HEAD)
```

---

## 5. How to Compile & Run

### Prerequisites
- Java Development Kit (JDK 11 or higher; tested on JDK 21).

### Option A: Using Provided Scripts
- **Windows (Local):** Double-click or run:
  ```powershell
  .\run.bat       # Runs the Java CLI version
  .\open_web.bat   # Launches the web app locally (http://localhost:8080)
  .\share_public.bat # Generates a public HTTPS link accessible by anyone on the internet!
  ```
- **Linux / macOS:**
  ```bash
  chmod +x run.sh
  ./run.sh
  ```

### Live Public Link (Accessible Worldwide)
- **Live URL:** [https://2d55e84af9dde2.lhr.life](https://2d55e84af9dde2.lhr.life)
- Anyone on mobile phones, tablets, or computers anywhere can open this link and interact with all Singly, Doubly, and Circular Linked List features.

### Option B: Manual Compilation & Execution
1. Compile the source code:
   ```bash
   javac -encoding UTF-8 -d bin src/com/dsa/linkedlist/*.java
   ```
2. Run the interactive visualizer:
   ```bash
   java -cp bin com.dsa.linkedlist.Main
   ```
3. Run the 5 automated demonstrations:
   ```bash
   java -cp bin com.dsa.linkedlist.Main --demo
   ```

---

## 6. Command Reference

| Command | Shorthand | Parameters | Example |
| :--- | :--- | :--- | :--- |
| `create` | - | `[val1 val2 ...]` | `create 10 20 30` |
| `insert_begin` | `ib` | `<val>` | `insert_begin 5` |
| `insert_end` | `ie` | `<val>` | `insert_end 50` |
| `insert_pos` | `ip` | `<val> <pos>` | `insert_pos 25 3` |
| `delete_begin` | `db` | - | `delete_begin` |
| `delete_end` | `de` | - | `delete_end` |
| `delete_pos` | `dp` | `<pos>` | `delete_pos 2` |
| `search` | - | `<val>` | `search 30` |
| `display` | `disp` | - | `display` |
| `display_backward` | `dispb` | - | `display_backward` *(DLL only)* |
| `reverse` | `rev` | - | `reverse` *(SLL & DLL)* |
| `count` | `size` | - | `count` |
| `clear` | - | - | `clear` |
| `help` | `?` | - | `help` |
| `exit` / `back` | - | - | `back` |

> **User-Friendly Parsing:** If arguments are omitted (e.g. typing only `insert_begin` or `insert_pos`), the CLI prompts for the missing values.

---

## 7. The 5 Demonstration Scenarios

### Scenario 1: Singly Linked List Full Lifecycle
**Goal:** Verify creation, insertions at head, tail, and middle, search highlighting, deletions from all locations, and in-place reversal.

**Input Sequence:**
```text
create 10 20 30
insert_begin 5
insert_end 40
insert_pos 25 4
search 25
delete_pos 4
delete_begin
delete_end
reverse
count
```

**Output Highlights:**
1. List initialized: `[10 | *] -> [20 | *] -> [30 | *] -> NULL`
2. Insert 5 at beginning: `[5 | *] -> [10 | *] -> [20 | *] -> [30 | *] -> NULL`
3. Insert 40 at end: `... -> [30 | *] -> [40 | *] -> NULL`
4. Insert 25 at position 4: `... -> [20 | *] -> [25 | *] -> [30 | *] -> ...`
5. Search 25: Returns `Found at position 4`, highlighted as `*25*`.
6. Deletions prune 25, 5, and 40, leaving `[10, 20, 30]`.
7. `reverse`: In-place pointer reversal updates head to 30:
   ```text
       HEAD                    TAIL
         v                       v
     [30 | *] -> [20 | *] -> [10 | *] -> NULL
   ```

---

### Scenario 2: Doubly Linked List Bidirectional Operations
**Goal:** Verify dual pointers (`prev` and `next`), backward traversal from `TAIL`, and in-place reversal by swapping pointers.

**Input Sequence:**
```text
create 100 200 300
insert_begin 50
insert_end 400
insert_pos 250 4
display_backward
search 200
delete_pos 4
reverse
display_forward
display_backward
```

**Output Highlights:**
1. Bidirectional visualization:
   ```text
     HEAD                                                      TAIL
       v                                                         v
     NULL <- [ 50 ] <-> [ 100 ] <-> [ 200 ] <-> [ 300 ] <-> [ 400 ] -> NULL
   ```
2. Backward Traversal output:
   `TAIL -> [400] -> [300] -> [200] -> [100] -> [50] -> HEAD (NULL)`
3. Reversal swaps `prev` and `next` for all nodes and switches `head` with `tail`.

---

### Scenario 3: Circular Linked List Wrap-around Operations
**Goal:** Verify the circular link where `tail.next == head` with no `NULL` termination.

**Input Sequence:**
```text
create 10 20 30 40
insert_begin 5
insert_end 50
insert_pos 25 4
search 30
delete_begin
delete_end
delete_pos 3
display
count
```

**Output Highlights:**
1. Wrap-around box diagram dynamically scales:
   ```text
               +-------------------------------+
               v                               |
     HEAD -> [10] -> [20] -> [30] -> [40] -----+
             (Pos 1) (Pos 2) (Pos 3) (Pos 4 TAIL)
     Circular Traversal: 10 -> 20 -> 30 -> 40 -> (loops back to 10)
     Metadata: Size = 4 | Head = 10 | Tail = 40 | Tail.next = 10 (HEAD)
   ```

---

### Scenario 4: Error Handling & Boundary Edge Cases
**Goal:** Demonstrate robust input validation, boundary checking, and underflow handling.

**Test Cases:**
1. **Underflow:** Call `delete_begin`, `delete_end`, `delete_pos 1` on an empty list:
   - Output: `[ERROR] Underflow! Cannot delete from an empty list.`
2. **Out-of-Bounds Insert:** Insert at position 5 on an empty list:
   - Output: `[ERROR] Invalid position (5). Valid range is 1 to 1.`
3. **Negative Index:** Insert at position -1:
   - Output: `[ERROR] Invalid position (-1). Valid range is 1 to 1.`
4. **Single-Element Boundary:** Insert 42, reverse single-node list (remains valid), delete 42 (transitions safely to empty list).
5. **Search Miss:** Search 100 in empty list -> `[WARN] List is empty. Element 100 not found.`

---

### Scenario 5: Real-World Workflow (Round-Robin Task Scheduler)
**Goal:** Apply Circular Linked Lists to a practical Operating Systems concept — Round-Robin CPU Process Scheduling.

**Execution Flow:**
1. Initialize queue with Process IDs: PID 101, PID 102, PID 103.
2. New High-Priority Process arrives (PID 99) at the head: `insert_begin 99`.
3. Background Process arrives (PID 104) at the tail: `insert_end 104`.
4. Process PID 99 completes CPU burst: `delete_begin`.
5. Process PID 102 finishes execution in the middle: `delete_pos 2`.
6. Final Active Circular Process Loop:
   ```text
               +--------------------------+
               v                          |
     HEAD -> [101] -> [103] -> [104] -----+
             (Pos 1)  (Pos 2)  (Pos 3 TAIL)
     Circular Traversal: 101 -> 103 -> 104 -> (loops back to 101)
   ```

---

## 8. Viva Voce & Lab Evaluation Questions

### Q1: What is the main difference between an Array and a Linked List?
- **Array:** Elements are stored in contiguous memory locations. Size is fixed (static) at compile time. Insertion and deletion at arbitrary positions require $O(n)$ element shifting.
- **Linked List:** Elements are dynamically allocated in non-contiguous heap memory, linked via pointers. Dynamic sizing with $O(1)$ insertion/deletion once the pointer location is known.

### Q2: Why is Doubly Linked List preferred over Singly Linked List for deletions?
In a Singly Linked List, deleting the last node or a given node requires traversing from the head to find the predecessor node ($O(n)$). In a Doubly Linked List with a `tail` pointer, deleting the last node takes $O(1)$ time because `tail.prev` immediately provides the predecessor.

### Q3: How do you detect an infinite loop in a Circular Linked List traversal?
By storing the reference to the `head` node and terminating the traversal loop as soon as `current.next == head` (or using Floyd's Cycle-Finding Algorithm with slow and fast pointers for arbitrary graphs).

### Q4: How is in-place reversal of a Singly Linked List implemented?
Using three pointers: `prev` (initialized to `null`), `curr` (initialized to `head`), and `nextNode`. In each step:
1. `nextNode = curr.next`
2. `curr.next = prev`
3. `prev = curr`
4. `curr = nextNode`
When `curr` becomes `null`, `head` is set to `prev`. This operates in $O(n)$ time and $O(1)$ auxiliary space.

### Q5: What happens to deleted nodes in Java vs. C?
In C, developers must explicitly call `free(node)` to avoid memory leaks. In Java, when a node is unlinked by removing all references pointing to it, the JVM's Garbage Collector automatically reclaims the memory.
