document.addEventListener("DOMContentLoaded", main)

function main() {
	document.getElementById("svg").addEventListener("click", click)
	document.getElementById("form").addEventListener("submit", validate)
}

function validate(e) {
	let form = document.getElementById("form")
	if (!checkX(form.x.value.trim().replace(/,/g, '.')) || !checkY(form.y.value.trim().replace(/,/g, '.')) ||
		!checkR(form.r.value.trim().replace(/,/g, '.'))) {
		e.preventDefault()
	}
}

function click(e) {
	let r = document.getElementById("form").r.value

	if (checkR(r)) {
		let svg = document.getElementById("svg")
		let point = svg.createSVGPoint()
		point.x = e.clientX
		point.y = e.clientY
		point = point.matrixTransform(svg.getScreenCTM().inverse())
		let dot = document.createElementNS("http://www.w3.org/2000/svg", "circle")
		dot.setAttributeNS(null, "cx", point.x)
		dot.setAttributeNS(null, "cy", point.y)
		dot.setAttributeNS(null, "r", 4)
		dot.setAttributeNS(null, "class", "svg-dot")
		svg.appendChild(dot)
		let x = parseFloat(parseFloat((point.x - center) / scale * r).toPrecision(12));
		let y = -parseFloat(parseFloat((point.y - center) / scale * r).toPrecision(12));

		console.log(x + " " + y)

		fetch(`ControllerServlet?x=${x}&y=${y}&r=${r}&clicked=1`).then(response => response.text()).then((response) => {
			let row = document.querySelector(".result_table").insertRow();
			row.innerHTML = response
		})
	}
}

function checkValue(name, value, left, right) {
	if (value === "") {
		alert(`${name} should not be empty`)
		return false
	} else if (isNaN(value)) {
		alert(`${name} should be a number`)
		return false
	} else if (value < left || right < value) {
		alert(`${name} should be in range (${left}; ${right})`)
		return false
	} else return true
}

function checkX(x) {
	return checkValue("X", x, -3, 5)
}

function checkY(y) {
	return checkValue("Y", y, -3, 5)
}

function checkR(r) {
	return checkValue("R", r, 2, 5)
}