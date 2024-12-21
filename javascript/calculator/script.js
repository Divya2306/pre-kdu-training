// Get input fields and buttons by their IDs
const num1Input = document.getElementById("num1");
const num2Input = document.getElementById("num2");
const addButton = document.getElementById("add");
const subtractButton = document.getElementById("subtract");
const multiplyButton = document.getElementById("multiply");
const divideButton = document.getElementById("divide");

// Function to perform the operation and show the result
function calculate(operation) {
  const num1 = parseFloat(num1Input.value);
  const num2 = parseFloat(num2Input.value);

  // Check for valid input
  if (isNaN(num1) || isNaN(num2)) {
    alert("Please enter valid numbers in both fields.");
    return;
  }

  let result;
  switch (operation) {
    case "add":
      result = num1 + num2;
      break;
    case "subtract":
      result = num1 - num2;
      break;
    case "multiply":
      result = num1 * num2;
      break;
    case "divide":
      if (num2 === 0) {
        alert("Cannot divide by zero");
        return;
      }
      result = num1 / num2;
      break;
    default:
      alert("Invalid operation");
      return;
  }

  // Display the result in an alert
  alert(`Result: ${result}`);
}

// Attach event listeners to buttons
addButton.addEventListener("click", () => calculate("add"));
subtractButton.addEventListener("click", () => calculate("subtract"));
multiplyButton.addEventListener("click", () => calculate("multiply"));
divideButton.addEventListener("click", () => calculate("divide"));
