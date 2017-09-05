function Enemy(vel_) {

	this.w = 20;
	this.x = random(this.w/2, width-this.w/2);
	this.y = -this.w/2;

	this.vel = vel_;

	this.update = function () {
		this.y = this.y + this.vel;
	}

	this.show = function () {
		push();
		translate(this.x, this.y);
		fill(211, 84, 0); 
		noStroke();
		// ellipse(0,0,this.w, this.w)
		rectMode(CENTER);
		rect(0,0,this.w,this.w);
		pop();
	}

	this.isOutsideCanvas = function () {
		if(this.y > height+this.w/2){
			return true;
		}
		else {
			return false;
		}
	}

}