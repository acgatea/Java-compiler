extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start ConstructorDeclarationNode *****
global _java.lang.Object_CONSTRUCTOR_Object#
_java.lang.Object_CONSTRUCTOR_Object#:
mov ebp, esp
; *****initialize nonstatic fields *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Object_METHOD_equals#Object#
_java.lang.Object_METHOD_equals#Object#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
mov eax, [__THIS]
mov eax, [eax] ; get this
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Object_BinOpEndLabel_2:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg other
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Object_BinOpEndLabel_3:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.Object_IfEQ_4
mov eax, 0
jmp _java.lang.Object_BinOpEndLabel_1
_java.lang.Object_IfEQ_4:
mov eax, 1
_java.lang.Object_BinOpEndLabel_1:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Object_METHOD_toString#
_java.lang.Object_METHOD_toString#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 18 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
mov ebx, 83
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 111
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 109
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 101
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 32
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 114
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 97
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 110
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 100
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 111
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 109
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 32
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 111
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 98
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 106
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 101
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 99
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
mov ebx, 116
mov [eax], ebx ; initialize char 
add eax, 4 
sub ecx, 1
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.Object_noError_7
call __exception ; nullpointerexception (for constructorcall)
_java.lang.Object_noError_7:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
extern _java.lang.String_CONSTRUCTOR_String#char$$#
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Object_BinOpEndLabel_5:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Object_METHOD_hashCode#
_java.lang.Object_METHOD_hashCode#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 42
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Object_BinOpEndLabel_8:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Object_METHOD_clone#
_java.lang.Object_METHOD_clone#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
mov eax, [__THIS]
mov eax, [eax] ; get this
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Object_BinOpEndLabel_9:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Object_METHOD_getClass#
_java.lang.Object_METHOD_getClass#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Object_BinOpEndLabel_10:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
