document.addEventListener("DOMContentLoaded", () => {
	let x;
	let y;
	let r;
	let radios = document.getElementsByName("x");
	document.getElementById("send").onclick = function() {
		radios.forEach(radio => {
			if (radio.checked)
				x = radio.value;
		});
		y = document.getElementById("y").value.trim().replace(/,/g, '.');;
		r = document.getElementById("r").value.trim().replace(/,/g, '.');;
		if (x == undefined)
            alert("Select the X value")
		else if (isNaN(y))
			alert("Y must be a number")
		else if (y == "")
            alert("Y should not be empty")
		else if (y.length > 10)
			alert("Y is to long")
        else if (y < -5 || 3 < y)
            alert("Y should be in range [-5; 3]")
		else if (isNaN(r))
			alert("R must be a number")
		else if (r == "")
            alert("R should not be empty")
		else if (r.length > 10)
			alert("R is to long")
        else if (r < 1 || 4 < r)
            alert("R should be in range [1; 4]")
        else {
			let data = new URLSearchParams();
            data.append("x", x)
            data.append("y", y)
            data.append("r", r)
            fetch("server.php?" + data, {
			    method:'GET',
				headers: {
				'Accept': 'text/html',
				'Content-Type': 'text/html'
				},
            }).then(response => response.text()).then((response) => {
                let row = document.querySelector(".result_table").insertRow();
                row.innerHTML = response
            })
        }
    }
});