// Initial load of the CAPTCHA image when the page is opened
loadCaptcha();

// Function to fetch and display a new CAPTCHA image
function loadCaptcha() {
    // Make a GET request to the 'generate' servlet to get CAPTCHA image and ID
    fetch('generate')
        .then(response => {
            // Read the Captcha-ID from response headers
            const captchaId = response.headers.get('Captcha-ID');
			const isRotated = response.headers.get('is-Rotated');
            

            // Store the Captcha-ID in a hidden input field for later validation
            document.getElementById('captcha_id').value = captchaId;
			
			toggleRotationNote(isRotated === "true");
		
            // Convert the response blob (binary data) into an image URL
            return response.blob();
        })
        .then(blob => {
            const imageUrl = URL.createObjectURL(blob);
            // Set the image source to the generated CAPTCHA
            document.getElementById('captcha').src = imageUrl;
        })
        .catch(error => console.error("Error loading CAPTCHA:", error));
}

// Get form and CAPTCHA input field references
const form = document.getElementById('captchaForm');
const capInputField = document.getElementById('capInput');

// Listen to form submit event
form.addEventListener('submit', function (e) {
    e.preventDefault(); // Prevent default form submission

    // Collect form data and convert to plain object
    const formData = new FormData(form);
    const plainData = {};
    formData.forEach((value, key) => {
        plainData[key] = value;
    });

    // Send the CAPTCHA and user input to the validate servlet using POST
    fetch('validate', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(plainData)
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            // If CAPTCHA is correct, redirect to success page
            window.location.href = 'success.html';
        } else {
            // If CAPTCHA is incorrect, show error and reload new CAPTCHA
            const msg = document.getElementById('result');
            const inputFiled = document.getElementById('capInput');
            msg.textContent = data.message;
            msg.classList.add('error'); // Add red error message style
            inputFiled.classList.add('borderError'); // Add error border to input
            capInputField.value = ""; // Clear the input field
            loadCaptcha(); // Load new CAPTCHA
        }
    })
    .catch(err => {
        console.error("Error during CAPTCHA validation:", err);
    });
	
	

});

function toggleRotationNote(show) {
	  const noteBox = document.getElementById("captcha-info");
	  if (show) {
	    noteBox.classList.remove('hidden-info');
		noteBox.classList.add('visible-info');
	  } else {
		noteBox.classList.remove('visible-info');
		noteBox.classList.add('hidden-info');
	  }
	}

