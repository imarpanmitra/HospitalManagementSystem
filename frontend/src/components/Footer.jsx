import React from "react";

const styles = {
  footer: {
    display: "block",
    height:"60px",
    margin: "auto",
    bottom: "0",
    right: "0",
    left: "0",
    width: "auto"
  }
}

function Footer() {
  return (
    <footer className="bg-secondary text-light" style={styles.footer}>
      <div className="footer-copyright text-center py-3">
        ©2023 Copyright: CureX
      </div>
    </footer>
  );
}

export default Footer;
