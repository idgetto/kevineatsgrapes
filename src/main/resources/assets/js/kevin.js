function Kevin(x_) {
	
	
	this.w = 70;

	this.y = 600 - this.w/2;
	this.x = x_;

	this.update = function () {
		//console.log(mouseX);
		this.x = this.x + 0.10*(mouseX-this.x);
		this.x = constrain(this.x, this.w/2, width-this.w/2);
	}

	this.show = function () {
		push();
		translate(this.x-this.w/2, this.y-this.w/2);
		// fill(0);
		// noStroke();
		// ellipse(0,0,this.w,this.w);
		image(img, 0,0)
		pop();
	}

}