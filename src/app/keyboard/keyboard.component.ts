import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-keyboard',
  templateUrl: './keyboard.component.html',
  styleUrls: ['./keyboard.component.css']
})
export class KeyboardComponent implements OnInit {

  public x = 0;
  public y = 0;
  private _words = '';
  @Output() words = new EventEmitter<string>();
  @Input() actionBtnText;
  @Input() actionBtnFn;

  public keywords = [ ];
  private lowerWords = [
    ['q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'],
    ['a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'delete'],
    ['Upper', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '&123'],
    ['deleteAll', 'space']
  ];
  private upperWords = [
    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'delete'],
    ['abc', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '&123'],
    ['deleteAll', 'space']
  ];
  private numberWords = [
    '1234567890'.split(''),
    '-_\/:;()¥&@'.split(''),
    ['Lower'].concat('.,?!\'"'.split('')).concat(['←']),
    ['deleteAll', 'space']
  ];

  is1Site(word) {
    const ignoreList = [
      'space',
      this.actionBtnText, 'deleteAll', 'Lower', '&123', '←'
    ];
    return -1 === ignoreList.indexOf(word);
  }
  is2Site(word) {
    const targetList = [
      this.actionBtnText, 'deleteAll', 'Lower', '&123', '←'
    ];
    return -1 !== targetList.indexOf(word);
  }
  getWord(val) {
    switch (val) {
      case 'delete': return '←';
      case 'deleteAll': return '清空';
      case 'space': return '';
      case 'Upper': return '';
      case 'Lower': return 'abc';
      default: return val;
    }
  }

  constructor() { }

  ngOnInit() {
    const keyboards = [this.lowerWords, this.upperWords, this.numberWords];
    for (const keyboard of keyboards) {
      const textArr = this.actionBtnText ? [this.actionBtnText] : [];
      const lastLine = keyboard[keyboard.length - 1];
      keyboard[keyboard.length - 1] = lastLine.concat(textArr);
    }
    this.keywords = this.lowerWords;

    const fixX = () => {
      const word = this.keywords[this.y][this.x];
      if (typeof word === 'string') {
        return;
      }
      if (!word) {
        this.x = this.keywords[this.y].length - 1;
      }
    };

    this.keyUp = () => {
      if (this.y === 0) {
        return;
      }
      this.y--;
      fixX();
    };
    this.keyDown = () => {
      if (this.y === (this.keywords.length - 1)) {
        return;
      }
      this.y++;
      fixX();
    };
    this.keyLeft = () => {
      if (this.x === 0) {
        this.x = this.keywords[this.y].length - 1;
        return;
      }
      this.x--;
    };
    this.keyRight = () => {
      if (this.x >= (this.keywords[this.y].length - 1)) {
        this.x = 0;
        return;
      }
      this.x++;
    };
    this.keyEnter = (word = this.keywords[this.y][this.x]) => {
      switch (word) {
        case 'space':
          this._words += ' '; break;
        case '←':
          this.keyEnter('delete');
          break;
        case 'delete':
          if (this._words.length === 0) {
            break;
          }
          this._words = this._words.slice(0, this._words.length - 1);
          break;
        case this.actionBtnText:
          if (this.actionBtnFn) {
            this.actionBtnFn();
          }
          break;
        case 'ABC':
          this.keywords = this.upperWords;
          break;
        case 'Upper':
          this.keyEnter('ABC');
          break;
        case 'abc':
          this.keywords = this.lowerWords;
          break;
        case 'Lower':
        this.keyEnter('abc');
          break;
        case '&123':
          this.keywords = this.numberWords;
          fixX();
          break;
        case 'deleteAll':
          this._words = '';
          break;
        default:
          this._words += word; break;
      }
      this.words.emit(this._words);
    };
  }
  keyUp() { }
  keyDown() { }
  keyLeft() { }
  keyRight() { }
  keyEnter(val?) { }

}
