// Get references to the DOM elements
const counterValue = document.getElementById("counter-value");
const incrementButton = document.getElementById("increment");
const decrementButton = document.getElementById("decrement");

// Initialize the counter value
let count = 0;

// Update the counter display
function updateCounter() {
  counterValue.textContent = count;
}

// Increment the counter value
incrementButton.addEventListener("click", () => {
  count++;
  updateCounter();
});

// Decrement the counter value
decrementButton.addEventListener("click", () => {
  count--;
  updateCounter();
});
