/**
 * Interactive Linked List Visualizer - Core Engine
 * Featuring Step-by-Step C Program Synchronization, Line-by-Line Highlighting,
 * Dynamic Memory Watcher, and Graphical Animation.
 */

// =============================================================================
// DATA STRUCTURE DEFINITIONS (MANUAL IMPLEMENTATION)
// =============================================================================

class SNode {
  constructor(data) {
    this.data = data;
    this.next = null;
  }
}

class DNode {
  constructor(data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }
}

// -----------------------------------------------------------------------------
// Singly Linked List
// -----------------------------------------------------------------------------
class SinglyList {
  constructor() {
    this.head = null;
    this.size = 0;
  }

  clear() {
    this.head = null;
    this.size = 0;
  }

  create(values) {
    this.clear();
    for (const val of values) {
      this.insertEnd(val);
    }
  }

  insertBegin(val) {
    const node = new SNode(val);
    node.next = this.head;
    this.head = node;
    this.size++;
  }

  insertEnd(val) {
    const node = new SNode(val);
    if (!this.head) {
      this.head = node;
    } else {
      let curr = this.head;
      while (curr.next) {
        curr = curr.next;
      }
      curr.next = node;
    }
    this.size++;
  }

  insertAtPosition(val, pos) {
    if (pos < 1 || pos > this.size + 1) return false;
    if (pos === 1) {
      this.insertBegin(val);
      return true;
    }
    const node = new SNode(val);
    let curr = this.head;
    for (let i = 1; i < pos - 1; i++) {
      curr = curr.next;
    }
    node.next = curr.next;
    curr.next = node;
    this.size++;
    return true;
  }

  deleteBegin() {
    if (!this.head) return null;
    const removed = this.head.data;
    this.head = this.head.next;
    this.size--;
    return removed;
  }

  deleteEnd() {
    if (!this.head) return null;
    if (!this.head.next) {
      const removed = this.head.data;
      this.head = null;
      this.size = 0;
      return removed;
    }
    let curr = this.head;
    while (curr.next.next) {
      curr = curr.next;
    }
    const removed = curr.next.data;
    curr.next = null;
    this.size--;
    return removed;
  }

  deleteAtPosition(pos) {
    if (!this.head || pos < 1 || pos > this.size) return null;
    if (pos === 1) return this.deleteBegin();
    let curr = this.head;
    for (let i = 1; i < pos - 1; i++) {
      curr = curr.next;
    }
    const removed = curr.next.data;
    curr.next = curr.next.next;
    this.size--;
    return removed;
  }

  search(key) {
    const positions = [];
    let curr = this.head;
    let idx = 1;
    while (curr) {
      if (curr.data === key) positions.push(idx);
      curr = curr.next;
      idx++;
    }
    return positions;
  }

  reverse() {
    if (!this.head || !this.head.next) return;
    let prev = null;
    let curr = this.head;
    let nextNode = null;
    while (curr) {
      nextNode = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nextNode;
    }
    this.head = prev;
  }

  toArray() {
    const arr = [];
    let curr = this.head;
    while (curr) {
      arr.push(curr.data);
      curr = curr.next;
    }
    return arr;
  }
}

// -----------------------------------------------------------------------------
// Doubly Linked List
// -----------------------------------------------------------------------------
class DoublyList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  create(values) {
    this.clear();
    for (const val of values) {
      this.insertEnd(val);
    }
  }

  insertBegin(val) {
    const node = new DNode(val);
    if (!this.head) {
      this.head = node;
      this.tail = node;
    } else {
      node.next = this.head;
      this.head.prev = node;
      this.head = node;
    }
    this.size++;
  }

  insertEnd(val) {
    const node = new DNode(val);
    if (!this.head) {
      this.head = node;
      this.tail = node;
    } else {
      this.tail.next = node;
      node.prev = this.tail;
      this.tail = node;
    }
    this.size++;
  }

  insertAtPosition(val, pos) {
    if (pos < 1 || pos > this.size + 1) return false;
    if (pos === 1) {
      this.insertBegin(val);
      return true;
    }
    if (pos === this.size + 1) {
      this.insertEnd(val);
      return true;
    }
    let curr = this.head;
    for (let i = 1; i < pos - 1; i++) {
      curr = curr.next;
    }
    const node = new DNode(val);
    node.next = curr.next;
    node.prev = curr;
    if (curr.next) curr.next.prev = node;
    curr.next = node;
    this.size++;
    return true;
  }

  deleteBegin() {
    if (!this.head) return null;
    const removed = this.head.data;
    if (this.head === this.tail) {
      this.head = null;
      this.tail = null;
    } else {
      this.head = this.head.next;
      this.head.prev = null;
    }
    this.size--;
    return removed;
  }

  deleteEnd() {
    if (!this.head) return null;
    const removed = this.tail.data;
    if (this.head === this.tail) {
      this.head = null;
      this.tail = null;
    } else {
      this.tail = this.tail.prev;
      this.tail.next = null;
    }
    this.size--;
    return removed;
  }

  deleteAtPosition(pos) {
    if (!this.head || pos < 1 || pos > this.size) return null;
    if (pos === 1) return this.deleteBegin();
    if (pos === this.size) return this.deleteEnd();

    let curr = this.head;
    for (let i = 1; i < pos; i++) {
      curr = curr.next;
    }
    const removed = curr.data;
    curr.prev.next = curr.next;
    curr.next.prev = curr.prev;
    this.size--;
    return removed;
  }

  search(key) {
    const positions = [];
    let curr = this.head;
    let idx = 1;
    while (curr) {
      if (curr.data === key) positions.push(idx);
      curr = curr.next;
      idx++;
    }
    return positions;
  }

  reverse() {
    if (!this.head || this.head === this.tail) return;
    let curr = this.head;
    let temp = null;
    while (curr) {
      temp = curr.prev;
      curr.prev = curr.next;
      curr.next = temp;
      curr = curr.prev;
    }
    this.tail = this.head;
    this.head = temp.prev;
  }

  toArray() {
    const arr = [];
    let curr = this.head;
    while (curr) {
      arr.push(curr.data);
      curr = curr.next;
    }
    return arr;
  }

  toArrayBackward() {
    const arr = [];
    let curr = this.tail;
    while (curr) {
      arr.push(curr.data);
      curr = curr.prev;
    }
    return arr;
  }
}

// -----------------------------------------------------------------------------
// Circular Linked List
// -----------------------------------------------------------------------------
class CircularList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  create(values) {
    this.clear();
    for (const val of values) {
      this.insertEnd(val);
    }
  }

  insertBegin(val) {
    const node = new SNode(val);
    if (!this.head) {
      this.head = node;
      this.tail = node;
      node.next = this.head;
    } else {
      node.next = this.head;
      this.tail.next = node;
      this.head = node;
    }
    this.size++;
  }

  insertEnd(val) {
    const node = new SNode(val);
    if (!this.head) {
      this.head = node;
      this.tail = node;
      node.next = this.head;
    } else {
      this.tail.next = node;
      node.next = this.head;
      this.tail = node;
    }
    this.size++;
  }

  insertAtPosition(val, pos) {
    if (pos < 1 || pos > this.size + 1) return false;
    if (pos === 1) {
      this.insertBegin(val);
      return true;
    }
    if (pos === this.size + 1) {
      this.insertEnd(val);
      return true;
    }
    let curr = this.head;
    for (let i = 1; i < pos - 1; i++) {
      curr = curr.next;
    }
    const node = new SNode(val);
    node.next = curr.next;
    curr.next = node;
    this.size++;
    return true;
  }

  deleteBegin() {
    if (!this.head) return null;
    const removed = this.head.data;
    if (this.head === this.tail) {
      this.head = null;
      this.tail = null;
    } else {
      this.head = this.head.next;
      this.tail.next = this.head;
    }
    this.size--;
    return removed;
  }

  deleteEnd() {
    if (!this.head) return null;
    const removed = this.tail.data;
    if (this.head === this.tail) {
      this.head = null;
      this.tail = null;
    } else {
      let curr = this.head;
      while (curr.next !== this.tail) {
        curr = curr.next;
      }
      curr.next = this.head;
      this.tail = curr;
    }
    this.size--;
    return removed;
  }

  deleteAtPosition(pos) {
    if (!this.head || pos < 1 || pos > this.size) return null;
    if (pos === 1) return this.deleteBegin();
    if (pos === this.size) return this.deleteEnd();

    let curr = this.head;
    for (let i = 1; i < pos - 1; i++) {
      curr = curr.next;
    }
    const removed = curr.next.data;
    curr.next = curr.next.next;
    this.size--;
    return removed;
  }

  search(key) {
    const positions = [];
    if (!this.head) return positions;
    let curr = this.head;
    let idx = 1;
    do {
      if (curr.data === key) positions.push(idx);
      curr = curr.next;
      idx++;
    } while (curr !== this.head);
    return positions;
  }

  toArray() {
    const arr = [];
    if (!this.head) return arr;
    let curr = this.head;
    do {
      arr.push(curr.data);
      curr = curr.next;
    } while (curr !== this.head);
    return arr;
  }
}

// =============================================================================
// C CODE SNIPPET DATABASE (SYNCHRONIZED PER OPERATION & LIST TYPE)
// =============================================================================

const C_PROGRAMS = {
  singly: {
    insert_begin: [
      "// Singly Linked List: Insert at Beginning",
      "void insert_begin(struct Node** head, int val) {",
      "    // 1. Allocate heap memory for new node",
      "    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));",
      "    newNode->data = val;",
      "    // 2. Link newNode->next to current head",
      "    newNode->next = *head;",
      "    // 3. Move HEAD pointer to newNode",
      "    *head = newNode;",
      "}"
    ],
    insert_end: [
      "// Singly Linked List: Insert at End",
      "void insert_end(struct Node** head, int val) {",
      "    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));",
      "    newNode->data = val;",
      "    newNode->next = NULL;",
      "    if (*head == NULL) {",
      "        *head = newNode;",
      "        return;",
      "    }",
      "    struct Node* curr = *head;",
      "    while (curr->next != NULL) {",
      "        curr = curr->next;",
      "    }",
      "    curr->next = newNode;",
      "}"
    ],
    insert_pos: [
      "// Singly Linked List: Insert at Position",
      "void insert_pos(struct Node** head, int val, int pos) {",
      "    if (pos == 1) { insert_begin(head, val); return; }",
      "    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));",
      "    newNode->data = val;",
      "    struct Node* curr = *head;",
      "    for (int i = 1; i < pos - 1 && curr != NULL; i++) {",
      "        curr = curr->next;",
      "    }",
      "    newNode->next = curr->next;",
      "    curr->next = newNode;",
      "}"
    ],
    delete_begin: [
      "// Singly Linked List: Delete from Beginning",
      "void delete_begin(struct Node** head) {",
      "    if (*head == NULL) return; // Underflow",
      "    struct Node* temp = *head;",
      "    *head = (*head)->next;",
      "    free(temp); // Release memory",
      "}"
    ],
    delete_end: [
      "// Singly Linked List: Delete from End",
      "void delete_end(struct Node** head) {",
      "    if (*head == NULL) return;",
      "    if ((*head)->next == NULL) {",
      "        free(*head);",
      "        *head = NULL;",
      "        return;",
      "    }",
      "    struct Node* curr = *head;",
      "    while (curr->next->next != NULL) {",
      "        curr = curr->next;",
      "    }",
      "    free(curr->next);",
      "    curr->next = NULL;",
      "}"
    ],
    delete_pos: [
      "// Singly Linked List: Delete at Position",
      "void delete_pos(struct Node** head, int pos) {",
      "    if (*head == NULL) return;",
      "    if (pos == 1) { delete_begin(head); return; }",
      "    struct Node* curr = *head;",
      "    for (int i = 1; i < pos - 1 && curr->next != NULL; i++) {",
      "        curr = curr->next;",
      "    }",
      "    struct Node* temp = curr->next;",
      "    curr->next = temp->next;",
      "    free(temp);",
      "}"
    ],
    search: [
      "// Singly Linked List: Search Element",
      "int search(struct Node* head, int key) {",
      "    struct Node* curr = head;",
      "    int pos = 1;",
      "    while (curr != NULL) {",
      "        if (curr->data == key) return pos;",
      "        curr = curr->next;",
      "        pos++;",
      "    }",
      "    return -1; // Not found",
      "}"
    ],
    reverse: [
      "// Singly Linked List: In-Place Pointer Reversal",
      "void reverse(struct Node** head) {",
      "    struct Node* prev = NULL;",
      "    struct Node* curr = *head;",
      "    struct Node* next = NULL;",
      "    while (curr != NULL) {",
      "        next = curr->next;  // Save next",
      "        curr->next = prev;  // Reverse pointer",
      "        prev = curr;        // Advance prev",
      "        curr = next;        // Advance curr",
      "    }",
      "    *head = prev;",
      "}"
    ],
    create: [
      "// Singly Linked List: Bulk Creation",
      "struct Node* create_list(int arr[], int n) {",
      "    struct Node* head = NULL;",
      "    for (int i = 0; i < n; i++) {",
      "        insert_end(&head, arr[i]);",
      "    }",
      "    return head;",
      "}"
    ]
  },
  doubly: {
    insert_begin: [
      "// Doubly Linked List: Insert at Beginning",
      "void insert_begin(struct DNode** head, int val) {",
      "    struct DNode* newNode = (struct DNode*)malloc(sizeof(struct DNode));",
      "    newNode->data = val;",
      "    newNode->prev = NULL;",
      "    newNode->next = *head;",
      "    if (*head != NULL) (*head)->prev = newNode;",
      "    *head = newNode;",
      "}"
    ],
    insert_end: [
      "// Doubly Linked List: Insert at End",
      "void insert_end(struct DNode** head, int val) {",
      "    struct DNode* newNode = (struct DNode*)malloc(sizeof(struct DNode));",
      "    newNode->data = val; newNode->next = NULL;",
      "    if (*head == NULL) { newNode->prev = NULL; *head = newNode; return; }",
      "    struct DNode* curr = *head;",
      "    while (curr->next != NULL) curr = curr->next;",
      "    curr->next = newNode;",
      "    newNode->prev = curr;",
      "}"
    ],
    insert_pos: [
      "// Doubly Linked List: Insert at Position",
      "void insert_pos(struct DNode** head, int val, int pos) {",
      "    if (pos == 1) { insert_begin(head, val); return; }",
      "    struct DNode* curr = *head;",
      "    for (int i = 1; i < pos - 1 && curr != NULL; i++) curr = curr->next;",
      "    struct DNode* newNode = (struct DNode*)malloc(sizeof(struct DNode));",
      "    newNode->data = val; newNode->next = curr->next; newNode->prev = curr;",
      "    if (curr->next != NULL) curr->next->prev = newNode;",
      "    curr->next = newNode;",
      "}"
    ],
    delete_begin: [
      "// Doubly Linked List: Delete Beginning",
      "void delete_begin(struct DNode** head) {",
      "    if (*head == NULL) return;",
      "    struct DNode* temp = *head;",
      "    *head = (*head)->next;",
      "    if (*head != NULL) (*head)->prev = NULL;",
      "    free(temp);",
      "}"
    ],
    delete_end: [
      "// Doubly Linked List: Delete End",
      "void delete_end(struct DNode** head) {",
      "    if (*head == NULL) return;",
      "    struct DNode* curr = *head;",
      "    if (curr->next == NULL) { free(curr); *head = NULL; return; }",
      "    while (curr->next != NULL) curr = curr->next;",
      "    curr->prev->next = NULL;",
      "    free(curr);",
      "}"
    ],
    delete_pos: [
      "// Doubly Linked List: Delete at Position",
      "void delete_pos(struct DNode** head, int pos) {",
      "    if (*head == NULL) return;",
      "    struct DNode* curr = *head;",
      "    for (int i = 1; i < pos && curr != NULL; i++) curr = curr->next;",
      "    if (curr->prev != NULL) curr->prev->next = curr->next;",
      "    if (curr->next != NULL) curr->next->prev = curr->prev;",
      "    free(curr);",
      "}"
    ],
    search: [
      "// Doubly Linked List: Search",
      "int search(struct DNode* head, int key) {",
      "    struct DNode* curr = head; int pos = 1;",
      "    while (curr != NULL) {",
      "        if (curr->data == key) return pos;",
      "        curr = curr->next; pos++;",
      "    }",
      "    return -1;",
      "}"
    ],
    reverse: [
      "// Doubly Linked List: Reverse (Swap next and prev)",
      "void reverse(struct DNode** head) {",
      "    struct DNode* curr = *head; struct DNode* temp = NULL;",
      "    while (curr != NULL) {",
      "        temp = curr->prev; curr->prev = curr->next; curr->next = temp;",
      "        curr = curr->prev; // Move backward using swapped pointer",
      "    }",
      "    if (temp != NULL) *head = temp->prev;",
      "}"
    ],
    create: [
      "// Doubly Linked List: Bulk Creation",
      "struct DNode* create_list(int arr[], int n) {",
      "    struct DNode* head = NULL;",
      "    for (int i = 0; i < n; i++) insert_end(&head, arr[i]);",
      "    return head;",
      "}"
    ]
  },
  circular: {
    insert_begin: [
      "// Circular Linked List: Insert at Beginning",
      "void insert_begin(struct Node** head, int val) {",
      "    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));",
      "    newNode->data = val;",
      "    if (*head == NULL) { newNode->next = newNode; *head = newNode; return; }",
      "    struct Node* curr = *head;",
      "    while (curr->next != *head) curr = curr->next;",
      "    newNode->next = *head;",
      "    curr->next = newNode; // Wrap-around link updated",
      "    *head = newNode;",
      "}"
    ],
    insert_end: [
      "// Circular Linked List: Insert at End",
      "void insert_end(struct Node** head, int val) {",
      "    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));",
      "    newNode->data = val;",
      "    if (*head == NULL) { newNode->next = newNode; *head = newNode; return; }",
      "    struct Node* curr = *head;",
      "    while (curr->next != *head) curr = curr->next;",
      "    curr->next = newNode;",
      "    newNode->next = *head; // Completes cycle",
      "}"
    ],
    insert_pos: [
      "// Circular Linked List: Insert at Position",
      "void insert_pos(struct Node** head, int val, int pos) {",
      "    if (pos == 1) { insert_begin(head, val); return; }",
      "    struct Node* curr = *head;",
      "    for (int i = 1; i < pos - 1; i++) curr = curr->next;",
      "    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));",
      "    newNode->data = val; newNode->next = curr->next; curr->next = newNode;",
      "}"
    ],
    delete_begin: [
      "// Circular Linked List: Delete Beginning",
      "void delete_begin(struct Node** head) {",
      "    if (*head == NULL) return;",
      "    if ((*head)->next == *head) { free(*head); *head = NULL; return; }",
      "    struct Node* curr = *head;",
      "    while (curr->next != *head) curr = curr->next;",
      "    struct Node* temp = *head;",
      "    curr->next = (*head)->next;",
      "    *head = (*head)->next;",
      "    free(temp);",
      "}"
    ],
    delete_end: [
      "// Circular Linked List: Delete End",
      "void delete_end(struct Node** head) {",
      "    if (*head == NULL) return;",
      "    if ((*head)->next == *head) { free(*head); *head = NULL; return; }",
      "    struct Node* curr = *head;",
      "    while (curr->next->next != *head) curr = curr->next;",
      "    free(curr->next);",
      "    curr->next = *head; // Re-link cycle to head",
      "}"
    ],
    delete_pos: [
      "// Circular Linked List: Delete at Position",
      "void delete_pos(struct Node** head, int pos) {",
      "    if (pos == 1) { delete_begin(head); return; }",
      "    struct Node* curr = *head;",
      "    for (int i = 1; i < pos - 1; i++) curr = curr->next;",
      "    struct Node* temp = curr->next;",
      "    curr->next = temp->next;",
      "    free(temp);",
      "}"
    ],
    search: [
      "// Circular Linked List: Search with Cycle Termination",
      "int search(struct Node* head, int key) {",
      "    if (head == NULL) return -1;",
      "    struct Node* curr = head; int pos = 1;",
      "    do {",
      "        if (curr->data == key) return pos;",
      "        curr = curr->next; pos++;",
      "    } while (curr != head);",
      "    return -1;",
      "}"
    ],
    create: [
      "// Circular Linked List: Bulk Creation",
      "struct Node* create_list(int arr[], int n) {",
      "    struct Node* head = NULL;",
      "    for (int i = 0; i < n; i++) insert_end(&head, arr[i]);",
      "    return head;",
      "}"
    ]
  }
};

// =============================================================================
// STATE & APPLICATION CONTROLLER
// =============================================================================

let currentType = 'singly'; // 'singly' | 'doubly' | 'circular'
const lists = {
  singly: new SinglyList(),
  doubly: new DoublyList(),
  circular: new CircularList(),
};

// Default seed values
lists.singly.create([10, 20, 30]);
lists.doubly.create([10, 20, 30]);
lists.circular.create([10, 20, 30]);

let highlightedIndices = [];
let isAsciiVisible = true;

// Step Playback State
let executionSteps = [];
let currentStepIndex = 0;
let isPlaying = false;
let playInterval = null;
let playbackSpeed = 1000; // ms

// =============================================================================
// STEP EXECUTION ENGINE
// =============================================================================

function setExecutionSteps(steps, funcName = 'operation') {
  executionSteps = steps;
  currentStepIndex = 0;
  pause();

  renderCProgram(funcName);
  renderStep(currentStepIndex);
}

function renderStep(idx) {
  if (executionSteps.length === 0) return;
  const step = executionSteps[idx];

  // Update progress text
  document.getElementById('step-progress-text').textContent = `Step ${idx + 1} / ${executionSteps.length}`;

  // Update Step Explanation
  document.getElementById('step-title').textContent = step.title;
  document.getElementById('step-desc').textContent = step.desc;

  // Highlight line in C Code
  highlightCLine(step.codeLine);

  // Update Memory & Variable Watcher
  updateVariableWatch(step.variables);

  // Render graphical nodes snapshot
  renderNodesSnapshot(step);

  // Update metrics
  updateMetrics(step.complexity || 'O(1)');

  // Render terminal ASCII
  renderAsciiDiagram();

  // Enable/disable buttons
  document.getElementById('btn-step-prev').disabled = idx === 0;
  document.getElementById('btn-step-next').disabled = idx === executionSteps.length - 1;
}

function nextStep() {
  if (currentStepIndex < executionSteps.length - 1) {
    currentStepIndex++;
    renderStep(currentStepIndex);
  } else {
    pause();
  }
}

function prevStep() {
  if (currentStepIndex > 0) {
    currentStepIndex--;
    renderStep(currentStepIndex);
  }
}

function togglePlayPause() {
  if (isPlaying) {
    pause();
  } else {
    play();
  }
}

function play() {
  if (executionSteps.length === 0) return;
  if (currentStepIndex >= executionSteps.length - 1) {
    currentStepIndex = 0;
  }
  isPlaying = true;
  document.getElementById('btn-play-pause').textContent = '⏸ Pause';
  playInterval = setInterval(() => {
    if (currentStepIndex < executionSteps.length - 1) {
      nextStep();
    } else {
      pause();
    }
  }, playbackSpeed);
}

function pause() {
  isPlaying = false;
  document.getElementById('btn-play-pause').textContent = '▶ Play';
  if (playInterval) {
    clearInterval(playInterval);
    playInterval = null;
  }
}

function updatePlaybackSpeed(ms) {
  playbackSpeed = parseInt(ms);
  document.getElementById('speed-display').textContent = `${(playbackSpeed / 1000).toFixed(1)}s`;
  if (isPlaying) {
    pause();
    play();
  }
}

// -----------------------------------------------------------------------------
// Graphical Rendering for Step Snapshot
// -----------------------------------------------------------------------------
function renderNodesSnapshot(step) {
  const container = document.getElementById('node-chain-container');
  const floatingArea = document.getElementById('floating-node-area');
  container.innerHTML = '';
  floatingArea.innerHTML = '';

  // 1. Render Floating Node (newly allocated in heap before link)
  if (step.floatingNode) {
    floatingArea.innerHTML = `
      <div class="floating-node-card">
        <span class="floating-label">heap malloc:</span>
        <div class="node-data-slot" style="color: var(--purple-glow); font-weight: bold;">
          [ ${step.floatingNode.data} | &bull; ]
        </div>
        <span class="floating-label">(newNode)</span>
      </div>
    `;
  }

  const nodes = step.nodes || [];
  if (nodes.length === 0) {
    container.innerHTML = `
      <div class="empty-state">
        <span class="empty-state-icon">📭</span>
        <span>HEAD &rarr; NULL (Empty List)</span>
      </div>
    `;
    return;
  }

  // Prepend NULL for Doubly Linked List
  if (currentType === 'doubly') {
    const nullLeft = document.createElement('div');
    nullLeft.className = 'connector-group';
    nullLeft.innerHTML = `
      <span class="null-badge">NULL</span>
      <span class="arrow-line arrow-bi">&larr;</span>
    `;
    container.appendChild(nullLeft);
  }

  nodes.forEach((val, idx) => {
    const isHead = idx === 0;
    const isTail = idx === nodes.length - 1;
    const isSingle = nodes.length === 1;
    const isTarget = step.targetNodeIdx === idx + 1;
    const isToDelete = step.deleteNodeIdx === idx + 1;

    const nodeWrap = document.createElement('div');
    nodeWrap.className = 'node-wrapper';
    nodeWrap.id = `node-wrapper-${idx + 1}`;

    // Pointer Overhead
    let pointerOverhead = '';
    if (step.activePointer && step.activePointer.idx === idx + 1) {
      pointerOverhead = `<div class="pointer-overhead curr-tag">${step.activePointer.label}<span class="pointer-arrow-down">&darr;</span></div>`;
    } else if (isSingle) {
      pointerOverhead = `<div class="pointer-overhead head-tail-tag">HEAD / TAIL<span class="pointer-arrow-down">&darr;</span></div>`;
    } else if (isHead) {
      pointerOverhead = `<div class="pointer-overhead head-tag">HEAD<span class="pointer-arrow-down">&darr;</span></div>`;
    } else if (isTail) {
      pointerOverhead = `<div class="pointer-overhead tail-tag">TAIL<span class="pointer-arrow-down">&darr;</span></div>`;
    }

    let innerStructure = '';
    if (currentType === 'singly') {
      innerStructure = `
        <div class="node-data-slot">${val}</div>
        <div class="node-next-slot">&bull;</div>
      `;
    } else if (currentType === 'doubly') {
      innerStructure = `
        <div class="node-prev-slot">&bull;</div>
        <div class="node-data-slot">${val}</div>
        <div class="node-next-slot">&bull;</div>
      `;
    } else {
      // Circular
      innerStructure = `
        <div class="node-data-slot">${val}</div>
        <div class="node-next-slot">&bull;</div>
      `;
    }

    let cardClass = 'node-card';
    if (isTarget) cardClass += ' active-target';
    if (isToDelete) cardClass += ' to-delete';
    if (highlightedIndices.includes(idx + 1)) cardClass += ' highlighted';

    nodeWrap.innerHTML = `
      ${pointerOverhead}
      <div class="${cardClass}" id="node-card-${idx + 1}">
        ${innerStructure}
      </div>
      <div class="node-index-tag">Pos ${idx + 1}</div>
    `;

    container.appendChild(nodeWrap);

    // Connector Arrow
    if (!isTail) {
      const conn = document.createElement('div');
      conn.className = 'connector-group';
      conn.innerHTML = currentType === 'doubly'
        ? `<span class="arrow-line arrow-bi">&harr;</span>`
        : `<span class="arrow-line">&rarr;</span>`;
      container.appendChild(conn);
    } else {
      if (currentType === 'singly' || currentType === 'doubly') {
        const nullRight = document.createElement('div');
        nullRight.className = 'connector-group';
        nullRight.innerHTML = `
          <span class="arrow-line">&rarr;</span>
          <span class="null-badge">NULL</span>
        `;
        container.appendChild(nullRight);
      }
    }
  });

  if (currentType === 'circular' && nodes.length > 0) {
    renderCircularSvgLoop(container);
  }
}

function renderCircularSvgLoop(container) {
  setTimeout(() => {
    const firstNode = container.querySelector('#node-card-1');
    const lastNode = container.querySelector('.node-wrapper:last-child .node-card');
    if (!firstNode || !lastNode) return;

    const firstRect = firstNode.getBoundingClientRect();
    const lastRect = lastNode.getBoundingClientRect();
    const containerRect = container.getBoundingClientRect();

    const startX = lastRect.right - containerRect.left + 8;
    const startY = lastRect.top - containerRect.top + lastRect.height / 2;
    const targetX = firstRect.left - containerRect.left + firstRect.width / 2;
    const targetY = firstRect.top - containerRect.top;

    const existingSvg = container.querySelector('.circular-svg-loop');
    if (existingSvg) existingSvg.remove();

    const svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    svg.setAttribute('class', 'circular-svg-loop');
    svg.style.width = `${containerRect.width}px`;
    svg.style.height = `${containerRect.height + 60}px`;

    const topArcY = Math.max(10, targetY - 35);
    const pathD = `M ${startX} ${startY} 
                   H ${startX + 18} 
                   V ${topArcY} 
                   H ${targetX} 
                   V ${targetY - 5}`;

    const path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
    path.setAttribute('d', pathD);
    path.setAttribute('fill', 'none');
    path.setAttribute('stroke', '#38bdf8');
    path.setAttribute('stroke-width', '2.5');
    path.setAttribute('stroke-dasharray', '5 4');

    const marker = document.createElementNS('http://www.w3.org/2000/svg', 'polygon');
    marker.setAttribute('points', `${targetX - 5},${targetY - 5} ${targetX + 5},${targetY - 5} ${targetX},${targetY}`);
    marker.setAttribute('fill', '#38bdf8');

    svg.appendChild(path);
    svg.appendChild(marker);
    container.appendChild(svg);
  }, 50);
}

// -----------------------------------------------------------------------------
// C Code Panel Syntax Highlighting & Line Stepper
// -----------------------------------------------------------------------------
function renderCProgram(opName = 'insert_begin') {
  const container = document.getElementById('c-code-container');
  container.innerHTML = '';

  const programLines = (C_PROGRAMS[currentType] && C_PROGRAMS[currentType][opName])
    ? C_PROGRAMS[currentType][opName]
    : C_PROGRAMS.singly.insert_begin;

  document.getElementById('c-function-title').innerHTML = `main.c &bull; <code>${opName}()</code>`;

  programLines.forEach((lineText, idx) => {
    const lineNum = idx + 1;
    const lineDiv = document.createElement('div');
    lineDiv.className = 'code-line';
    lineDiv.id = `c-line-${lineNum}`;

    const formattedCode = syntaxHighlight(lineText);

    lineDiv.innerHTML = `
      <span class="code-arrow-indicator">&blacktriangleright;</span>
      <span class="code-line-num">${lineNum}</span>
      <span class="code-text">${formattedCode}</span>
    `;

    container.appendChild(lineDiv);
  });
}

function highlightCLine(lineNum) {
  document.querySelectorAll('.code-line').forEach(el => el.classList.remove('active'));
  if (!lineNum) return;

  const targetLine = document.getElementById(`c-line-${lineNum}`);
  if (targetLine) {
    targetLine.classList.add('active');
    targetLine.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
  }
}

function syntaxHighlight(code) {
  return code
    .replace(/\/\/.*/g, match => `<span class="syntax-comment">${match}</span>`)
    .replace(/\b(struct|void|int|return|while|for|if|else|sizeof)\b/g, match => `<span class="syntax-keyword">${match}</span>`)
    .replace(/\b(Node|DNode)\b/g, match => `<span class="syntax-type">${match}</span>`)
    .replace(/\b(malloc|free|insert_begin|insert_end|insert_pos|delete_begin|delete_end|delete_pos|search|reverse)\b/g, match => `<span class="syntax-func">${match}</span>`)
    .replace(/\b(NULL|[0-9]+)\b/g, match => `<span class="syntax-val">${match}</span>`);
}

function updateVariableWatch(vars = {}) {
  document.getElementById('watch-head').textContent = vars.head || 'NULL';
  document.getElementById('watch-curr').textContent = vars.curr || 'NULL';
  document.getElementById('watch-newnode').textContent = vars.newNode || 'NULL';
  document.getElementById('watch-temp').textContent = vars.temp || vars.prev || 'NULL';
}

function updateMetrics(complexity = 'O(1)') {
  const list = lists[currentType];
  const arr = list.toArray();
  const size = arr.length;

  document.getElementById('metric-count').textContent = size;
  document.getElementById('metric-head').textContent = size > 0 ? arr[0] : 'NULL';
  document.getElementById('metric-tail').textContent = size > 0 ? arr[size - 1] : 'NULL';
  document.getElementById('metric-complexity').textContent = complexity;

  const reverseBtn = document.getElementById('btn-reverse');
  const backwardBtn = document.getElementById('btn-display-backward');
  const tagInsertEndTime = document.getElementById('tag-insert-end-time');
  const tagDeleteEndTime = document.getElementById('tag-delete-end-time');

  if (currentType === 'singly') {
    reverseBtn.style.display = 'inline-flex';
    backwardBtn.style.display = 'none';
    tagInsertEndTime.textContent = 'Time: O(n)';
    tagDeleteEndTime.textContent = 'Time: O(n)';
  } else if (currentType === 'doubly') {
    reverseBtn.style.display = 'inline-flex';
    backwardBtn.style.display = 'inline-flex';
    tagInsertEndTime.textContent = 'Time: O(1)';
    tagDeleteEndTime.textContent = 'Time: O(1)';
  } else {
    reverseBtn.style.display = 'none';
    backwardBtn.style.display = 'none';
    tagInsertEndTime.textContent = 'Time: O(1)';
    tagDeleteEndTime.textContent = 'Time: O(n)';
  }
}

function setOperationToast(message) {
  const toast = document.getElementById('operation-toast');
  toast.textContent = message;
}

function showBannerToast(msg) {
  const banner = document.getElementById('toast-banner');
  banner.textContent = msg;
  banner.classList.add('show');
  setTimeout(() => banner.classList.remove('show'), 3000);
}

// -----------------------------------------------------------------------------
// Real-time ASCII Diagram Synthesizer
// -----------------------------------------------------------------------------
function renderAsciiDiagram() {
  const canvas = document.getElementById('ascii-canvas');
  const arr = lists[currentType].toArray();

  if (arr.length === 0) {
    canvas.textContent = currentType === 'doubly'
      ? 'NULL <- HEAD / TAIL -> NULL (Empty List)'
      : 'HEAD -> NULL (Empty List)';
    return;
  }

  let text = '';
  if (currentType === 'singly') {
    text = `HEAD                                      TAIL\n  v                                         v\n`;
    const nodes = arr.map(v => `[${v} | *]`).join(' -> ');
    const indices = arr.map((_, i) => `(Pos ${i + 1})`).join('    ');
    text += `${nodes} -> NULL\n${indices}\n\n`;
    text += `Traversal: ${arr.join(' -> ')} -> NULL`;
  } else if (currentType === 'doubly') {
    text = `HEAD                                        TAIL\n  v                                           v\n`;
    const nodes = arr.map(v => `[${v}]`).join(' <-> ');
    const indices = arr.map((_, i) => `(Pos ${i + 1})`).join('   ');
    text += `NULL <- ${nodes} -> NULL\n        ${indices}\n\n`;
    text += `Forward : ${arr.join(' <-> ')} -> NULL`;
  } else {
    // Circular
    const nodes = arr.map(v => `[${v}]`).join(' -> ');
    const dashes = '-'.repeat(Math.max(10, nodes.length + 8));
    text = `       +${dashes}+\n       v${' '.repeat(dashes.length)}|\n`;
    text += `HEAD -> ${nodes} -----+\n`;
    const indices = arr.map((_, i) => `(Pos ${i + 1})`).join('  ');
    text += `        ${indices}\n\n`;
    text += `Circular Traversal: ${arr.join(' -> ')} -> (loops back to ${arr[0]})`;
  }

  canvas.textContent = text;
}

function toggleAsciiDrawer() {
  const canvas = document.getElementById('ascii-canvas');
  isAsciiVisible = !isAsciiVisible;
  canvas.style.display = isAsciiVisible ? 'block' : 'none';
}

// =============================================================================
// OPERATION HANDLERS (GENERATING DETAILED STEP-BY-STEP TRACES)
// =============================================================================

function switchListType(type) {
  currentType = type;
  highlightedIndices = [];

  document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
  document.getElementById(`tab-${type}`).classList.add('active');

  const names = {
    singly: 'Singly Linked List',
    doubly: 'Doubly Linked List',
    circular: 'Circular Linked List',
  };

  document.getElementById('current-list-badge').textContent = names[type];
  document.getElementById('stage-list-name').textContent = `${names[type]} Diagram`;

  renderCProgram('insert_begin');
  setExecutionSteps([{
    codeLine: 2,
    title: `Switched context to ${names[type]}`,
    desc: `Active list changed. Ready to perform operations and trace C code.`,
    variables: { head: lists[type].head ? lists[type].head.data : 'NULL' },
    nodes: lists[type].toArray(),
    complexity: 'O(1)'
  }], 'insert_begin');

  showBannerToast(`Switched to ${names[type]}`);
}

// -----------------------------------------------------------------------------
// 1. Insert at Beginning Trace
// -----------------------------------------------------------------------------
function handleInsertBegin() {
  const val = parseInt(document.getElementById('input-insert-begin').value);
  if (isNaN(val)) return;

  const list = lists[currentType];
  const initialNodes = list.toArray();
  const oldHeadVal = initialNodes.length > 0 ? initialNodes[0] : 'NULL';

  const steps = [
    {
      codeLine: 2,
      title: "Step 1: Function Invocation",
      desc: `Calling insert_begin(&head, ${val}). Passing head pointer by reference.`,
      variables: { head: oldHeadVal, newNode: 'NULL', curr: 'NULL', temp: 'NULL' },
      nodes: [...initialNodes],
      complexity: 'O(1)'
    },
    {
      codeLine: 4,
      title: "Step 2: Dynamic Memory Allocation (malloc)",
      desc: `Allocating sizeof(struct Node) bytes in heap memory for newNode with data = ${val}.`,
      variables: { head: oldHeadVal, newNode: `0x7FF (${val})`, curr: 'NULL', temp: 'NULL' },
      nodes: [...initialNodes],
      floatingNode: { data: val },
      complexity: 'O(1)'
    },
    {
      codeLine: 7,
      title: "Step 3: Pointer Assignment (newNode->next)",
      desc: `Setting newNode->next = *head. The new node now references the current head node (${oldHeadVal}).`,
      variables: { head: oldHeadVal, newNode: `0x7FF (${val})` },
      nodes: [...initialNodes],
      floatingNode: { data: val },
      complexity: 'O(1)'
    },
    {
      codeLine: 9,
      title: "Step 4: Updating HEAD Pointer (*head = newNode)",
      desc: `Repointing HEAD to the newly allocated node. The new node is officially at Position 1.`,
      variables: { head: val, newNode: `${val}` },
      nodes: [val, ...initialNodes],
      targetNodeIdx: 1,
      complexity: 'O(1)'
    }
  ];

  list.insertBegin(val);
  setExecutionSteps(steps, 'insert_begin');
  setOperationToast(`Inserted ${val} at beginning`);
}

// -----------------------------------------------------------------------------
// 2. Insert at End Trace
// -----------------------------------------------------------------------------
function handleInsertEnd() {
  const val = parseInt(document.getElementById('input-insert-end').value);
  if (isNaN(val)) return;

  const list = lists[currentType];
  const initialNodes = list.toArray();
  const steps = [];

  steps.push({
    codeLine: 3,
    title: "Step 1: Allocate New Node",
    desc: `Allocating heap memory for newNode with data = ${val}, newNode->next = NULL.`,
    variables: { head: initialNodes[0] || 'NULL', newNode: val, curr: 'NULL' },
    nodes: [...initialNodes],
    floatingNode: { data: val },
    complexity: currentType === 'singly' ? 'O(n)' : 'O(1)'
  });

  if (initialNodes.length === 0) {
    steps.push({
      codeLine: 7,
      title: "Step 2: Empty List Check (*head == NULL)",
      desc: `The list is empty. Set *head = newNode. Head now directly points to ${val}.`,
      variables: { head: val, newNode: val, curr: 'NULL' },
      nodes: [val],
      targetNodeIdx: 1,
      complexity: 'O(1)'
    });
  } else {
    steps.push({
      codeLine: 10,
      title: "Step 2: Initialize Traversal Pointer",
      desc: `struct Node* curr = *head. Pointer curr begins at head node (${initialNodes[0]}).`,
      variables: { head: initialNodes[0], curr: initialNodes[0], newNode: val },
      nodes: [...initialNodes],
      floatingNode: { data: val },
      activePointer: { label: 'curr', idx: 1 },
      complexity: 'O(n)'
    });

    for (let i = 1; i < initialNodes.length; i++) {
      steps.push({
        codeLine: 12,
        title: `Step 3.${i}: Traverse Forward (curr = curr->next)`,
        desc: `curr->next != NULL. Advance curr to next node (Value: ${initialNodes[i]}).`,
        variables: { head: initialNodes[0], curr: initialNodes[i], newNode: val },
        nodes: [...initialNodes],
        floatingNode: { data: val },
        activePointer: { label: 'curr', idx: i + 1 },
        complexity: 'O(n)'
      });
    }

    steps.push({
      codeLine: 14,
      title: "Step 4: Link Tail to newNode (curr->next = newNode)",
      desc: `Reached the end node (${initialNodes[initialNodes.length - 1]}). Linking its next pointer to newNode.`,
      variables: { head: initialNodes[0], curr: initialNodes[initialNodes.length - 1], newNode: val },
      nodes: [...initialNodes, val],
      targetNodeIdx: initialNodes.length + 1,
      complexity: 'O(n)'
    });
  }

  list.insertEnd(val);
  setExecutionSteps(steps, 'insert_end');
  setOperationToast(`Inserted ${val} at end`);
}

// -----------------------------------------------------------------------------
// 3. Insert at Position Trace
// -----------------------------------------------------------------------------
function handleInsertPos() {
  const val = parseInt(document.getElementById('input-insert-pos-val').value);
  const pos = parseInt(document.getElementById('input-insert-pos-idx').value);
  if (isNaN(val) || isNaN(pos)) return;

  const list = lists[currentType];
  if (pos < 1 || pos > list.size + 1) {
    showBannerToast(`Invalid position! Range: 1 to ${list.size + 1}`);
    return;
  }

  if (pos === 1) {
    handleInsertBegin();
    return;
  }

  const initialNodes = list.toArray();
  const steps = [
    {
      codeLine: 4,
      title: "Step 1: Allocate Node in Heap",
      desc: `Allocating memory for newNode with value ${val}.`,
      variables: { head: initialNodes[0], newNode: val, curr: 'NULL' },
      nodes: [...initialNodes],
      floatingNode: { data: val },
      complexity: 'O(n)'
    },
    {
      codeLine: 6,
      title: "Step 2: Initialize curr at HEAD",
      desc: `curr = *head. Traversing to node right before target insertion index (Position ${pos - 1}).`,
      variables: { head: initialNodes[0], curr: initialNodes[0], newNode: val },
      nodes: [...initialNodes],
      floatingNode: { data: val },
      activePointer: { label: 'curr', idx: 1 },
      complexity: 'O(n)'
    }
  ];

  for (let i = 1; i < pos - 1; i++) {
    steps.push({
      codeLine: 8,
      title: `Step 3.${i}: Advance curr to Position ${i + 1}`,
      desc: `curr = curr->next. Traversing towards position ${pos - 1}.`,
      variables: { head: initialNodes[0], curr: initialNodes[i], newNode: val },
      nodes: [...initialNodes],
      floatingNode: { data: val },
      activePointer: { label: 'curr', idx: i + 1 },
      complexity: 'O(n)'
    });
  }

  const updatedNodes = [...initialNodes];
  updatedNodes.splice(pos - 1, 0, val);

  steps.push({
    codeLine: 10,
    title: "Step 4: Rewire Pointers (newNode->next = curr->next, curr->next = newNode)",
    desc: `Pointers linked! newNode points to subsequent node, and predecessor node (curr) points to newNode.`,
    variables: { head: initialNodes[0], curr: initialNodes[pos - 2], newNode: val },
    nodes: updatedNodes,
    targetNodeIdx: pos,
    complexity: 'O(n)'
  });

  list.insertAtPosition(val, pos);
  setExecutionSteps(steps, 'insert_pos');
  setOperationToast(`Inserted ${val} at position ${pos}`);
}

// -----------------------------------------------------------------------------
// 4. Delete Beginning Trace
// -----------------------------------------------------------------------------
function handleDeleteBegin() {
  const list = lists[currentType];
  if (list.size === 0) {
    showBannerToast('List is empty! Underflow condition.');
    setExecutionSteps([{
      codeLine: 3,
      title: "Underflow Check (*head == NULL)",
      desc: "List is already empty. Nothing to delete.",
      variables: { head: 'NULL' },
      nodes: [],
      complexity: 'O(1)'
    }], 'delete_begin');
    return;
  }

  const initialNodes = list.toArray();
  const deletedVal = initialNodes[0];
  const newHeadVal = initialNodes.length > 1 ? initialNodes[1] : 'NULL';

  const steps = [
    {
      codeLine: 4,
      title: "Step 1: Save Head in Temporary Pointer (temp = *head)",
      desc: `Setting temp pointer to current head node (${deletedVal}) so we can free it later.`,
      variables: { head: deletedVal, temp: deletedVal },
      nodes: [...initialNodes],
      activePointer: { label: 'temp', idx: 1 },
      complexity: 'O(1)'
    },
    {
      codeLine: 5,
      title: "Step 2: Advance HEAD (*head = (*head)->next)",
      desc: `Moving HEAD pointer forward to the next node (${newHeadVal}).`,
      variables: { head: newHeadVal, temp: deletedVal },
      nodes: [...initialNodes],
      deleteNodeIdx: 1,
      complexity: 'O(1)'
    },
    {
      codeLine: 6,
      title: "Step 3: Free Memory (free(temp))",
      desc: `Calling free(temp) to return node memory to OS heap. Node ${deletedVal} is deleted!`,
      variables: { head: newHeadVal, temp: 'DEALLOCATED' },
      nodes: initialNodes.slice(1),
      complexity: 'O(1)'
    }
  ];

  list.deleteBegin();
  setExecutionSteps(steps, 'delete_begin');
  setOperationToast(`Deleted ${deletedVal} from beginning`);
}

// -----------------------------------------------------------------------------
// 5. Delete End Trace
// -----------------------------------------------------------------------------
function handleDeleteEnd() {
  const list = lists[currentType];
  if (list.size === 0) {
    showBannerToast('List is empty! Underflow condition.');
    return;
  }

  const initialNodes = list.toArray();
  const deletedVal = initialNodes[initialNodes.length - 1];

  const steps = [
    {
      codeLine: 3,
      title: "Step 1: Empty & Single-Node Check",
      desc: "Checking if list is empty or has only one element.",
      variables: { head: initialNodes[0] },
      nodes: [...initialNodes],
      complexity: currentType === 'doubly' ? 'O(1)' : 'O(n)'
    }
  ];

  if (initialNodes.length === 1) {
    steps.push({
      codeLine: 5,
      title: "Step 2: Single Node Deletion (free(*head))",
      desc: `Only 1 node in list. Freeing head node (${deletedVal}) and setting *head = NULL.`,
      variables: { head: 'NULL' },
      nodes: [],
      complexity: 'O(1)'
    });
  } else {
    steps.push({
      codeLine: 10,
      title: "Step 2: Initialize curr to Traverse",
      desc: "Traversing to the second-to-last node (predecessor of tail).",
      variables: { head: initialNodes[0], curr: initialNodes[0] },
      nodes: [...initialNodes],
      activePointer: { label: 'curr', idx: 1 },
      complexity: 'O(n)'
    });

    for (let i = 1; i < initialNodes.length - 1; i++) {
      steps.push({
        codeLine: 12,
        title: `Step 3.${i}: Advance curr to Position ${i + 1}`,
        desc: `curr = curr->next. Visiting node with value ${initialNodes[i]}.`,
        variables: { head: initialNodes[0], curr: initialNodes[i] },
        nodes: [...initialNodes],
        activePointer: { label: 'curr', idx: i + 1 },
        complexity: 'O(n)'
      });
    }

    steps.push({
      codeLine: 14,
      title: "Step 4: Free Tail Node & Set curr->next = NULL",
      desc: `free(curr->next). Deallocated tail node (${deletedVal}) and terminated list with NULL.`,
      variables: { head: initialNodes[0], curr: initialNodes[initialNodes.length - 2] },
      nodes: initialNodes.slice(0, -1),
      complexity: 'O(n)'
    });
  }

  list.deleteEnd();
  setExecutionSteps(steps, 'delete_end');
  setOperationToast(`Deleted ${deletedVal} from end`);
}

// -----------------------------------------------------------------------------
// 6. Delete at Position Trace
// -----------------------------------------------------------------------------
function handleDeletePos() {
  const pos = parseInt(document.getElementById('input-delete-pos-idx').value);
  if (isNaN(pos)) return;

  const list = lists[currentType];
  if (pos < 1 || pos > list.size) {
    showBannerToast(`Invalid position! Range: 1 to ${list.size}`);
    return;
  }

  if (pos === 1) {
    handleDeleteBegin();
    return;
  }

  const initialNodes = list.toArray();
  const deletedVal = initialNodes[pos - 1];

  const steps = [
    {
      codeLine: 5,
      title: "Step 1: Initialize curr at HEAD",
      desc: `curr = *head. Traversing to node right before position ${pos}.`,
      variables: { head: initialNodes[0], curr: initialNodes[0] },
      nodes: [...initialNodes],
      activePointer: { label: 'curr', idx: 1 },
      complexity: 'O(n)'
    }
  ];

  for (let i = 1; i < pos - 1; i++) {
    steps.push({
      codeLine: 7,
      title: `Step 2.${i}: Advance curr towards Position ${pos - 1}`,
      desc: `curr = curr->next. Current node value: ${initialNodes[i]}.`,
      variables: { head: initialNodes[0], curr: initialNodes[i] },
      nodes: [...initialNodes],
      activePointer: { label: 'curr', idx: i + 1 },
      complexity: 'O(n)'
    });
  }

  steps.push({
    codeLine: 9,
    title: "Step 3: Point temp to Target Node (temp = curr->next)",
    desc: `Isolating node to delete (${deletedVal}) with temp pointer.`,
    variables: { head: initialNodes[0], curr: initialNodes[pos - 2], temp: deletedVal },
    nodes: [...initialNodes],
    activePointer: { label: 'temp', idx: pos },
    deleteNodeIdx: pos,
    complexity: 'O(n)'
  });

  const remaining = [...initialNodes];
  remaining.splice(pos - 1, 1);

  steps.push({
    codeLine: 11,
    title: "Step 4: Bypass & Free (curr->next = temp->next; free(temp))",
    desc: `Predecessor node now bypasses deleted node. Memory freed!`,
    variables: { head: initialNodes[0], curr: initialNodes[pos - 2], temp: 'FREED' },
    nodes: remaining,
    complexity: 'O(n)'
  });

  list.deleteAtPosition(pos);
  setExecutionSteps(steps, 'delete_pos');
  setOperationToast(`Deleted ${deletedVal} from position ${pos}`);
}

// -----------------------------------------------------------------------------
// 7. Search Trace
// -----------------------------------------------------------------------------
function handleSearch() {
  const key = parseInt(document.getElementById('input-search-val').value);
  if (isNaN(key)) return;

  const list = lists[currentType];
  const initialNodes = list.toArray();
  const steps = [];

  steps.push({
    codeLine: 3,
    title: "Step 1: Initialize Search Pointer (curr = head)",
    desc: `Starting linear search for key = ${key} at head node (${initialNodes[0] || 'NULL'}).`,
    variables: { head: initialNodes[0] || 'NULL', curr: initialNodes[0] || 'NULL', key: key },
    nodes: [...initialNodes],
    activePointer: { label: 'curr', idx: 1 },
    complexity: 'O(n)'
  });

  let found = false;
  let foundPos = -1;

  for (let i = 0; i < initialNodes.length; i++) {
    const isMatch = initialNodes[i] === key;
    steps.push({
      codeLine: isMatch ? 6 : 7,
      title: `Step 2.${i + 1}: Compare curr->data == ${key}`,
      desc: isMatch
        ? `Match found! curr->data (${initialNodes[i]}) equals key (${key}) at 1-based Position ${i + 1}.`
        : `Comparing curr->data (${initialNodes[i]}) with key (${key}). Not a match. Moving forward.`,
      variables: { curr: initialNodes[i], pos: i + 1, key: key },
      nodes: [...initialNodes],
      activePointer: { label: 'curr', idx: i + 1 },
      targetNodeIdx: isMatch ? i + 1 : null,
      complexity: 'O(n)'
    });
    if (isMatch && !found) {
      found = true;
      foundPos = i + 1;
    }
  }

  steps.push({
    codeLine: found ? 6 : 10,
    title: found ? "Search Complete: Value Found!" : "Search Complete: Value Not Found",
    desc: found ? `Key ${key} was located at position ${foundPos}.` : `Key ${key} was not present in the linked list. Returning -1.`,
    variables: { key: key, result: found ? `Found at ${foundPos}` : -1 },
    nodes: [...initialNodes],
    targetNodeIdx: found ? foundPos : null,
    complexity: 'O(n)'
  });

  highlightedIndices = found ? [foundPos] : [];
  setExecutionSteps(steps, 'search');
  setOperationToast(found ? `Found ${key} at position ${foundPos}` : `${key} not found`);
}

// -----------------------------------------------------------------------------
// 8. In-Place Reversal Trace
// -----------------------------------------------------------------------------
function handleReverse() {
  const list = lists[currentType];
  if (list.size <= 1) {
    showBannerToast('List has 0 or 1 node. Nothing to reverse.');
    return;
  }

  const initialNodes = list.toArray();
  const steps = [
    {
      codeLine: 3,
      title: "Step 1: Initialize 3 Pointers (prev = NULL, curr = *head, next = NULL)",
      desc: "Setting up three tracking pointers to perform in-place pointer inversion.",
      variables: { prev: 'NULL', curr: initialNodes[0], next: 'NULL' },
      nodes: [...initialNodes],
      activePointer: { label: 'curr', idx: 1 },
      complexity: 'O(n)'
    }
  ];

  let currArr = [...initialNodes];
  for (let i = 0; i < initialNodes.length; i++) {
    steps.push({
      codeLine: 8,
      title: `Step 2.${i + 1}: Save next & Invert Pointer (curr->next = prev)`,
      desc: `Visiting node ${initialNodes[i]}. Saving its next reference, rewiring pointer backwards to prev (${i === 0 ? 'NULL' : initialNodes[i - 1]}).`,
      variables: { prev: i === 0 ? 'NULL' : initialNodes[i - 1], curr: initialNodes[i], next: initialNodes[i + 1] || 'NULL' },
      nodes: [...currArr],
      activePointer: { label: 'curr', idx: i + 1 },
      complexity: 'O(n)'
    });
  }

  const reversedNodes = [...initialNodes].reverse();
  steps.push({
    codeLine: 12,
    title: "Step 3: Update HEAD Pointer (*head = prev)",
    desc: `All pointer arrows are inverted! Updating *head to point to the new first node (${reversedNodes[0]}).`,
    variables: { head: reversedNodes[0], prev: reversedNodes[0], curr: 'NULL' },
    nodes: reversedNodes,
    complexity: 'O(n)'
  });

  list.reverse();
  setExecutionSteps(steps, 'reverse');
  setOperationToast('Reversed list in-place');
}

// -----------------------------------------------------------------------------
// 9. Bulk Create Trace
// -----------------------------------------------------------------------------
function handleCreate() {
  const raw = document.getElementById('input-create-vals').value;
  const vals = raw.split(/[\s,]+/).map(s => parseInt(s.trim())).filter(n => !isNaN(n));
  if (vals.length === 0) {
    showBannerToast('Please enter at least one valid number.');
    return;
  }

  const list = lists[currentType];
  list.create(vals);
  highlightedIndices = [];

  const steps = [
    {
      codeLine: 2,
      title: "Step 1: Initialize Empty Head (*head = NULL)",
      desc: "Resetting list pointer to NULL before populating array elements.",
      variables: { head: 'NULL' },
      nodes: [],
      complexity: 'O(1)'
    },
    {
      codeLine: 4,
      title: "Step 2: Sequentially Populate Array in Heap",
      desc: `Inserting ${vals.length} nodes: [${vals.join(', ')}].`,
      variables: { head: vals[0], total: vals.length },
      nodes: [...vals],
      complexity: 'O(n)'
    }
  ];

  setExecutionSteps(steps, 'create');
  setOperationToast(`Created list with ${vals.length} elements`);
}

function handleDisplayBackward() {
  if (currentType !== 'doubly') return;
  const list = lists.doubly;
  const backwardArr = list.toArrayBackward();
  if (backwardArr.length === 0) {
    showBannerToast('List is empty');
    return;
  }
  showBannerToast(`Backward: ${backwardArr.join(' \u2192 ')}`);
}

function handleClearList() {
  lists[currentType].clear();
  highlightedIndices = [];
  setExecutionSteps([{
    codeLine: 1,
    title: "List Cleared",
    desc: "Memory freed and HEAD set to NULL.",
    variables: { head: 'NULL' },
    nodes: [],
    complexity: 'O(1)'
  }], 'create');
  setOperationToast('List cleared');
}

// =============================================================================
// DEMONSTRATION SCENARIOS LAUNCHER
// =============================================================================

function runPresetDemo(num) {
  if (num === 1) {
    switchListType('singly');
    lists.singly.create([10, 20, 30]);
    handleInsertBegin();
  } else if (num === 2) {
    switchListType('doubly');
    lists.doubly.create([100, 200, 300]);
    handleInsertEnd();
  } else if (num === 3) {
    switchListType('circular');
    lists.circular.create([10, 20, 30, 40]);
    handleInsertBegin();
  } else if (num === 4) {
    switchListType('singly');
    lists.singly.clear();
    handleDeleteBegin();
  } else if (num === 5) {
    switchListType('circular');
    lists.circular.create([101, 102, 103]);
    handleInsertEnd();
  }
}

// Window resize listener to recompute circular SVG arc
window.addEventListener('resize', () => {
  if (currentType === 'circular') {
    renderNodesSnapshot(executionSteps[currentStepIndex] || { nodes: lists.circular.toArray() });
  }
});

// Initialize on DOM load
document.addEventListener('DOMContentLoaded', () => {
  renderCProgram('insert_begin');
  setExecutionSteps([{
    codeLine: 2,
    title: "Application Ready",
    desc: "Click any operation (Insert, Delete, Search, Reverse) to visualize execution line-by-line with standard C code!",
    variables: { head: lists.singly.head ? lists.singly.head.data : 'NULL' },
    nodes: lists.singly.toArray(),
    complexity: 'O(1)'
  }], 'insert_begin');
});
